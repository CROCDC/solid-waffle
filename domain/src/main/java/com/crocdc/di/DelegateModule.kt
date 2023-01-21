package com.crocdc.di

import com.crocdc.delegate.PokemonInfoDelegate
import com.crocdc.delegate.PokemonInfoDelegateImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DelegateModule {
    @Binds
    abstract fun bindPokemonInfoDelegate(
        pokemonInfoDelegate: PokemonInfoDelegateImp
    ): PokemonInfoDelegate
}