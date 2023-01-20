package com.crocdc.usecase

import com.crocdc.domain.model.FromEvolutionTo
import com.crocdc.domain.model.PokemonInfo
import com.crocdc.domain.model.PokemonSpecie
import kotlinx.coroutines.flow.Flow

interface PokemonInfoUseCase {
    val pokemonInfo: Flow<PokemonInfo?>
    val pokemonSpecie: Flow<PokemonSpecie?>
    val evolutions: Flow<List<FromEvolutionTo>>
    suspend fun setName(name: String)
}
