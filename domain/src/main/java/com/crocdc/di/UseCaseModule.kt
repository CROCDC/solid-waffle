package com.crocdc.di

import com.crocdc.usecase.AbilitiesUseCase
import com.crocdc.usecase.AbilitiesUseCaseImp
import com.crocdc.usecase.AreasUseCase
import com.crocdc.usecase.AreasUseCaseImp
import com.crocdc.usecase.EvolutionsUseCase
import com.crocdc.usecase.EvolutionsUseCaseImp
import com.crocdc.usecase.LocationAreaUseCase
import com.crocdc.usecase.LocationAreaUseCaseImp
import com.crocdc.usecase.MovesUseCase
import com.crocdc.usecase.MovesUseCaseImp
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

    @Binds
    abstract fun bindLocationAreaUseCase(
        useCase: LocationAreaUseCaseImp
    ): LocationAreaUseCase
}
