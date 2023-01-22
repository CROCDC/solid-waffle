package com.crocdc.datanetworking.di

import com.crocdc.datanetworking.datasource.EncountersDataSource
import com.crocdc.datanetworking.datasource.EncountersDataSourceProvider
import com.crocdc.datanetworking.datasource.EvolutionsDataSource
import com.crocdc.datanetworking.datasource.EvolutionsDataSourceProvider
import com.crocdc.datanetworking.datasource.PokemonDataSource
import com.crocdc.datanetworking.datasource.PokemonDataSourceProvider
import com.crocdc.datanetworking.datasource.PokemonSpeciesDataSource
import com.crocdc.datanetworking.datasource.PokemonSpeciesDataSourceProvider
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

    @Binds
    abstract fun bindPokemonSpeciesDataSource(
        dataSource: PokemonSpeciesDataSource
    ): PokemonSpeciesDataSourceProvider

    @Binds
    abstract fun bindEvolutionsDataSource(
        dataSource: EvolutionsDataSource
    ): EvolutionsDataSourceProvider

    @Binds
    abstract fun bindEncountersDataSource(
        dataSource: EncountersDataSource
    ): EncountersDataSourceProvider
}
