package com.crocdc.solidwaffle.di

import com.crocdc.domain.model.FromEvolutionTo
import com.crocdc.domain.model.Pokemon
import com.crocdc.solidwaffle.util.MockFactory
import com.crocdc.usecase.EvolutionsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeEvolutionsUseCaseImp : EvolutionsUseCase {
    override fun invoke(name: Flow<String?>): Flow<List<FromEvolutionTo>> = MutableStateFlow(
        listOf(
            FromEvolutionTo(
                Pokemon(
                    "Squirtle",
                    MockFactory.image
                ),
                12,
                Pokemon(
                    "wartortle",
                    MockFactory.image
                )
            )
        )
    )
}
