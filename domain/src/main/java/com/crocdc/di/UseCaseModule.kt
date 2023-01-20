package com.crocdc.di

import com.crocdc.usecase.PokemonInfoUseCase
import com.crocdc.usecase.PokemonInfoUseCaseImp
import com.crocdc.usecase.PokemonListingUseCase
import com.crocdc.usecase.PokemonListingUseCaseImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindPokemonInfoUseCase(
        useCase: PokemonInfoUseCaseImp
    ): PokemonInfoUseCase

    @Binds
    abstract fun bindPokemonListingUseCase(
        useCase: PokemonListingUseCaseImp
    ): PokemonListingUseCase
}