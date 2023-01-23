package com.crocdc.solidwaffle.di

import com.crocdc.domain.model.PokemonMove
import com.crocdc.solidwaffle.util.MockFactory
import com.crocdc.usecase.MovesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeMovesUseCaseImp : MovesUseCase {
    override fun invoke(name: Flow<String?>): Flow<List<PokemonMove>> = MutableStateFlow(
        listOf(
            MockFactory.move
        )
    )
}
