package com.crocdc.usecase

import com.crocdc.delegate.PokemonInfoDelegate
import com.crocdc.domain.model.PokemonInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonInfoUseCaseImp @Inject constructor(
    delegate: PokemonInfoDelegate
) : PokemonInfoUseCase, PokemonInfoDelegate by delegate {
    override val pokemonInfo: Flow<PokemonInfo?> = delegate.pokemonInfo
}
