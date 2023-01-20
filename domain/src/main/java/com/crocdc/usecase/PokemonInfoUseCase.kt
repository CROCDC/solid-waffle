package com.crocdc.usecase

import com.crocdc.datacore.model.PokemonInfo
import kotlinx.coroutines.flow.Flow

interface PokemonInfoUseCase {
    val pokemonInfo: Flow<PokemonInfo?>
    suspend fun setName(name: String)
}
