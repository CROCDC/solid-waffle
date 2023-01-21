package com.crocdc.usecase

import com.crocdc.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonListingUseCase {
    operator fun invoke(query: Flow<String?>): Flow<List<Pokemon>>
}