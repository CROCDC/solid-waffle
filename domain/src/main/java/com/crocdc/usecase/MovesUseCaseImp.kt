package com.crocdc.usecase

import com.crocdc.delegate.PokemonInfoDelegate
import com.crocdc.domain.model.Move
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovesUseCaseImp @Inject constructor(
    delegate: PokemonInfoDelegate
) : MovesUseCase, PokemonInfoDelegate by delegate {

    override val moves: Flow<List<Move>> = pokemonInfo.map {
        it?.moves ?: emptyList()
    }
}