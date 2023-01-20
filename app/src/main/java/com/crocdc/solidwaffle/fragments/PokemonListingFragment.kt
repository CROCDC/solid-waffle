package com.crocdc.solidwaffle.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.crocdc.solidwaffle.R
import com.crocdc.solidwaffle.adapter.PokemonListingAdapter
import com.crocdc.solidwaffle.databinding.FragmentPokemonListingBinding
import com.crocdc.solidwaffle.util.viewDataBinding
import com.crocdc.solidwaffle.vm.PokemonListingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokemonListingFragment : Fragment(R.layout.fragment_pokemon_listing) {

    private val binding: FragmentPokemonListingBinding by viewDataBinding()

    private val viewModel: PokemonListingViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pokemonAdapter = PokemonListingAdapter()
        binding.recycler.adapter = pokemonAdapter
        lifecycleScope.launch {
            viewModel.pokemons.collect {
                pokemonAdapter.submitList(it)
            }
        }
        binding.search.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean = false

                override fun onQueryTextChange(query: String?): Boolean {
                    lifecycleScope.launch {
                        viewModel.setQuery(query)
                    }
                    return true
                }
            }
        )
    }
}