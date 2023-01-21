package com.crocdc.usecase

import com.crocdc.domain.model.PokemonMove
import kotlinx.coroutines.flow.Flow

interface MovesUseCase {
    operator fun invoke(name: Flow<String?>): Flow<List<PokemonMove>>
}