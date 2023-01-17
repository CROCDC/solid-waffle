package com.crocdc.solidwaffle.fragments

import androidx.lifecycle.ViewModel
import com.crocdc.datacore.repos.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonListingViewModel @Inject constructor(repository: PokemonRepository) : ViewModel() {
    // TODO: Implement the ViewModel
}