package com.crocdc.datanetworking.di

import com.crocdc.datanetworking.datasource.PokemonDataSource
import com.crocdc.datanetworking.datasource.PokemonDataSourceProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindPokemonDataSource(
        dataSource: PokemonDataSource
    ): PokemonDataSourceProvider
}