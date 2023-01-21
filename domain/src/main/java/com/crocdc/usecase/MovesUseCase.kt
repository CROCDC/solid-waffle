package com.crocdc.usecase

import com.crocdc.delegate.PokemonInfoDelegate
import com.crocdc.domain.model.Move
import kotlinx.coroutines.flow.Flow

interface MovesUseCase : PokemonInfoDelegate {
    val moves: Flow<List<Move>>
}