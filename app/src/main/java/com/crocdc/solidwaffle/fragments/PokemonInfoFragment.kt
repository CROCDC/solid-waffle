package com.crocdc.solidwaffle.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.crocdc.solidwaffle.R
import com.crocdc.solidwaffle.adapter.PokemonInfoAdapter
import com.crocdc.solidwaffle.adapter.TypeAdapter
import com.crocdc.solidwaffle.databinding.FragmentPokemonInfoBinding
import com.crocdc.solidwaffle.fetchImage
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
        val adapter = TypeAdapter()
        binding.recyclerTypes.adapter = adapter
        binding.txtName.text = args.name
        lifecycleScope.launch {
            viewModel.setName(args.name)
            viewModel.pokemonInfo.collect {
                adapter.submitList(it?.types)
                it?.let {
                    binding.img.fetchImage(it.image)
                }
            }
        }
        binding.viewPager.adapter = PokemonInfoAdapter(this)
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