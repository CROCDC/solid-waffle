package com.crocdc.solidwaffle.di

import com.crocdc.di.UseCaseModule
import com.crocdc.usecase.AbilitiesUseCase
import com.crocdc.usecase.AreasUseCase
import com.crocdc.usecase.EvolutionsUseCase
import com.crocdc.usecase.LocationAreaUseCase
import com.crocdc.usecase.MovesUseCase
import com.crocdc.usecase.PokemonInfoUseCase
import com.crocdc.usecase.PokemonListingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [UseCaseModule::class]
)
class FakeUseCaseModule {

    @Provides
    fun bindPokemonInfoUseCase(): PokemonInfoUseCase = FakePokemonInfoUseCaseImp()

    @Provides
    fun bindPokemonListingUseCase(): PokemonListingUseCase = FakePokemonListingUseCaseImp()

    @Provides
    fun bindAbilitiesUseCase(): AbilitiesUseCase = FakeAbilitiesUseCaseImp()

    @Provides
    fun bindAreasUseCase(): AreasUseCase = FakeAreasUseCaseImp()

    @Provides
    fun bindEvolutionsUseCase(): EvolutionsUseCase = FakeEvolutionsUseCaseImp()

    @Provides
    fun bindMovesUseCase(): MovesUseCase = FakeMovesUseCaseImp()

    @Provides
    fun bindLocationAreaUseCase(): LocationAreaUseCase = FakeLocationAreaUseCaseImp()
}
