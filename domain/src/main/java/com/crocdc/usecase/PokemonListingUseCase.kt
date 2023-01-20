package com.crocdc.usecase

import com.crocdc.datacore.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonListingUseCase {
    val pokemons: Flow<List<Pokemon>>
    suspend fun setQuery(query: String?)
}