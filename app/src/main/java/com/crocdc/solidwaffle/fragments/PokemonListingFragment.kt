package com.crocdc.solidwaffle.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.crocdc.solidwaffle.R
import com.crocdc.solidwaffle.vm.PokemonListingViewModel

class PokemonListingFragment : Fragment(R.layout.fragment_pokemon_listing) {

    private val viewModel: PokemonListingViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}