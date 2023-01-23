package com.crocdc.solidwaffle.di

import com.crocdc.domain.model.PokemonInfo
import com.crocdc.usecase.PokemonInfoUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakePokemonInfoUseCaseImp : PokemonInfoUseCase {
    override fun invoke(name: Flow<String?>): Flow<PokemonInfo?> = flow { }
}
