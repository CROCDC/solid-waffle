package com.crocdc.solidwaffle.di

import com.crocdc.domain.model.Ability
import com.crocdc.domain.model.PokemonInfo
import com.crocdc.domain.model.PokemonMove
import com.crocdc.domain.model.Type
import com.crocdc.solidwaffle.util.MockFactory
import com.crocdc.usecase.PokemonInfoUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakePokemonInfoUseCaseImp : PokemonInfoUseCase {
    override fun invoke(name: Flow<String?>): Flow<PokemonInfo?> = MutableStateFlow(
        PokemonInfo(
            "Squirtle",
            MockFactory.image,
            "",
            types,
            listOf(
                Ability("name", false)
            ),
            listOf(
                PokemonMove("name")
            )
        )
    )

    companion object {
        const val name: String = "Squirtle"
        val types = listOf(Type.WATER)
    }
}
