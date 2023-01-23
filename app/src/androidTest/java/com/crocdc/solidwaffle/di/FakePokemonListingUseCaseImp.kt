package com.crocdc.solidwaffle.di

import com.crocdc.domain.model.Pokemon
import com.crocdc.solidwaffle.util.MockFactory
import com.crocdc.usecase.PokemonListingUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakePokemonListingUseCaseImp : PokemonListingUseCase {
    override fun invoke(query: Flow<String?>): Flow<List<Pokemon>> = MutableStateFlow(
        listOf(
            Pokemon(MockFactory.pokemonName, MockFactory.image)
        )
    )
}
