package com.crocdc.delegate

import com.crocdc.domain.model.PokemonInfo
import kotlinx.coroutines.flow.Flow

interface PokemonInfoUseCase {
    operator fun invoke(name: Flow<String?>): Flow<PokemonInfo?>
}
