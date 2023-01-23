package com.crocdc.solidwaffle.di

import com.crocdc.domain.model.LocationArea
import com.crocdc.domain.model.PokemonEncounter
import com.crocdc.solidwaffle.util.MockFactory
import com.crocdc.usecase.LocationAreaUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeLocationAreaUseCaseImp : LocationAreaUseCase {
    override fun invoke(id: Flow<String?>): Flow<LocationArea?> = MutableStateFlow(
        LocationArea(
            MockFactory.areaName,
            listOf(
                PokemonEncounter(
                    MockFactory.pokemonName,
                    MockFactory.image
                )
            )
        )
    )
}
