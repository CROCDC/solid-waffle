package com.crocdc.solidwaffle.fragments

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.crocdc.solidwaffle.R
import com.crocdc.solidwaffle.adapter.PokemonInfoAdapter
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
        val pokemonInfoAdapter = PokemonInfoAdapter(this)
        binding.viewPager.adapter = pokemonInfoAdapter
        lifecycleScope.launch {
            viewModel.setName(args.name)
            viewModel.pokemonInfo.collect {
                typeAdapter.submitList(it?.types)
                it?.let {
                    binding.img.fetchImage(it.image)
                    it.types.getOrNull(0)?.getColor()?.let {
                        binding.collapsing.setBackgroundColor(
                            ContextCompat.getColor(
                                requireContext(),
                                it
                            )
                        )
                    }
                }
            }
        }
        lifecycleScope.launch {
            viewModel.fragments.collect {
                pokemonInfoAdapter.setFragments(it)
            }
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> R.string.evolutions
                1 -> R.string.moves
                2 -> R.string.abilities
                3 -> R.string.areas
                else -> null
            }?.let {
                tab.setText(it)
            }
        }.attach()
    }
}