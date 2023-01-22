package com.crocdc.solidwaffle.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import androidx.viewpager2.widget.ViewPager2.SCROLL_STATE_IDLE
import com.crocdc.solidwaffle.R
import com.crocdc.solidwaffle.adapter.PokemonFragmentAdapter
import com.crocdc.solidwaffle.adapter.TypeAdapter
import com.crocdc.solidwaffle.databinding.FragmentPokemonInfoBinding
import com.crocdc.solidwaffle.fetchImage
import com.crocdc.solidwaffle.util.getColor
import com.crocdc.solidwaffle.util.viewDataBinding
import com.crocdc.solidwaffle.vm.PokemonInfoViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokemonInfoFragment : Fragment(R.layout.fragment_pokemon_info) {

    private val args: PokemonInfoFragmentArgs by navArgs()

    private val binding: FragmentPokemonInfoBinding by viewDataBinding()

    private val viewModel: PokemonInfoViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val typeAdapter = TypeAdapter()
        binding.recyclerTypes.adapter = typeAdapter
        binding.txtName.text = args.name
        val pokemonFragmentAdapter = PokemonFragmentAdapter(this)
        binding.viewPager.adapter = pokemonFragmentAdapter

        lifecycleScope.launch {
            viewModel.selectedImage.collect { it ->
                it?.image?.let { binding.img.fetchImage(it) }
            }
        }
        lifecycleScope.launch {
            viewModel.setName(args.name)
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.pokemonInfo.collect { info ->
                    typeAdapter.submitList(info?.types)
                    info?.let { it ->
                        it.types.getOrNull(0)?.getColor()?.let {
                            val color = ContextCompat.getColor(
                                requireContext(),
                                it
                            )
                            binding.collapsing.setBackgroundColor(color)
                            binding.tabLayout.setSelectedTabIndicatorColor(color)
                        }
                    }
                }
            }
        }
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.fragments.collect { fragments ->
                    pokemonFragmentAdapter.setFragments(fragments)
                    TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
                        fragments.getOrNull(position)?.let {
                            tab.setText(it.title)
                        }
                    }.attach()
                }
            }
        }
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.imageOptions.collect { images ->
                    binding.imgSelectImage.setOnClickListener {
                        val popupMenu = PopupMenu(it.context, it)
                        images.forEachIndexed { index, imageOption ->
                            popupMenu.menu.add(index, index, index, getString(imageOption.title))
                        }
                        popupMenu.menuInflater.inflate(R.menu.select_image_menu, popupMenu.menu)
                        popupMenu.setOnMenuItemClickListener { menuItem ->
                            lifecycleScope.launch { viewModel.setSelectedImage(images[menuItem.order]) }
                            true
                        }
                        popupMenu.show()
                    }
                }
            }
        }
    }
}
