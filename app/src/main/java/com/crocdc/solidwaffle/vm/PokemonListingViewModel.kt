package com.crocdc.solidwaffle.vm

import androidx.lifecycle.ViewModel
import com.crocdc.datacore.model.Pokemon
import com.crocdc.datacore.repos.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PokemonListingViewModel @Inject constructor(repository: PokemonRepository) : ViewModel() {

    val pokemons: Flow<List<Pokemon>> = repository.getPokemonsListing()
}
