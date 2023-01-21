package com.crocdc.usecase

import com.crocdc.delegate.PokemonInfoDelegate
import com.crocdc.domain.model.PokemonInfo
import kotlinx.coroutines.flow.Flow

interface PokemonInfoUseCase : PokemonInfoDelegate {
    override val pokemonInfo: Flow<PokemonInfo?>
}
