package com.crocdc.solidwaffle.vm

import androidx.lifecycle.ViewModel
import com.crocdc.datacore.model.Pokemon
import com.crocdc.datacore.repos.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class PokemonListingViewModel @Inject constructor(repository: PokemonRepository) : ViewModel() {

    private val query: MutableStateFlow<String?> = MutableStateFlow(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    val pokemons: Flow<List<Pokemon>> = query.flatMapLatest {
        repository.getPokemonsListing(it)
    }

    suspend fun setQuery(query: String?) {
        this.query.emit(query)
    }
}
