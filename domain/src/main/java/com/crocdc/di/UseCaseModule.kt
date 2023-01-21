package com.crocdc.di

import com.crocdc.delegate.AbilitiesUseCase
import com.crocdc.delegate.AbilitiesUseCaseImp
import com.crocdc.delegate.AreasUseCase
import com.crocdc.delegate.AreasUseCaseImp
import com.crocdc.delegate.EvolutionsUseCase
import com.crocdc.delegate.EvolutionsUseCaseImp
import com.crocdc.delegate.MovesUseCase
import com.crocdc.delegate.MovesUseCaseImp
import com.crocdc.delegate.PokemonInfoUseCase
import com.crocdc.delegate.PokemonInfoUseCaseImp
import com.crocdc.delegate.PokemonListingUseCase
import com.crocdc.delegate.PokemonListingUseCaseImp
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

    @Binds
    abstract fun bindAbilitiesUseCase(
        useCase: AbilitiesUseCaseImp
    ): AbilitiesUseCase

    @Binds
    abstract fun bindAreasUseCase(
        useCase: AreasUseCaseImp
    ): AreasUseCase

    @Binds
    abstract fun bindEvolutionsUseCase(
        useCase: EvolutionsUseCaseImp
    ): EvolutionsUseCase

    @Binds
    abstract fun bindMovesUseCase(
        useCase: MovesUseCaseImp
    ): MovesUseCase
}