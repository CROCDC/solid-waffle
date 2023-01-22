package com.crocdc.solidwaffle.vm

import androidx.lifecycle.ViewModel
import com.crocdc.domain.model.Pokemon
import com.crocdc.usecase.PokemonListingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class PokemonListingViewModel @Inject constructor(
    pokemonListingUseCase: PokemonListingUseCase
) : ViewModel() {
    private val query: MutableStateFlow<String?> = MutableStateFlow(null)

    val pokemons: Flow<List<Pokemon>> = pokemonListingUseCase.invoke(query)

    suspend fun setQuery(query: String?) {
        this.query.emit(query)
    }
}
