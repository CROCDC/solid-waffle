package com.crocdc.solidwaffle.di

import com.crocdc.domain.model.PokemonInfo
import com.crocdc.domain.model.Type
import com.crocdc.solidwaffle.util.MockFactory
import com.crocdc.usecase.PokemonInfoUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakePokemonInfoUseCaseImp : PokemonInfoUseCase {
    override fun invoke(name: Flow<String?>): Flow<PokemonInfo?> = MutableStateFlow(
        PokemonInfo(
            MockFactory.pokemonName,
            MockFactory.image,
            "",
            types,
            listOf(
                MockFactory.ability
            ),
            listOf(
                MockFactory.move
            )
        )
    )

    companion object {
        val types = listOf(Type.WATER)
    }
}
