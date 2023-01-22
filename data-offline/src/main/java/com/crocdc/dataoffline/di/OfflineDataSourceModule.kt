package com.crocdc.dataoffline.di

import com.crocdc.dataoffline.datasource.OfflineEncountersDataSource
import com.crocdc.dataoffline.datasource.OfflineEncountersDataSourceProvider
import com.crocdc.dataoffline.datasource.OfflineEvolutionsDataSource
import com.crocdc.dataoffline.datasource.OfflineEvolutionsDataSourceProvider
import com.crocdc.dataoffline.datasource.OfflineLocationAreaDataSource
import com.crocdc.dataoffline.datasource.OfflineLocationAreaDataSourceProvider
import com.crocdc.dataoffline.datasource.OfflinePokemonDataSource
import com.crocdc.dataoffline.datasource.OfflinePokemonDataSourceProvider
import com.crocdc.dataoffline.datasource.OfflinePokemonSpeciesDataSource
import com.crocdc.dataoffline.datasource.OfflinePokemonSpeciesDataSourceProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class OfflineDataSourceModule {

    @Binds
    abstract fun bindOfflinePokemonDataSource(
        dataSource: OfflinePokemonDataSource
    ): OfflinePokemonDataSourceProvider

    @Binds
    abstract fun bindOfflinePokemonSpeciesDataSource(
        dataSource: OfflinePokemonSpeciesDataSource
    ): OfflinePokemonSpeciesDataSourceProvider

    @Binds
    abstract fun bindOfflineEvolutionsDataSource(
        dataSource: OfflineEvolutionsDataSource
    ): OfflineEvolutionsDataSourceProvider

    @Binds
    abstract fun bindOfflineEncountersDataSource(
        dataSource: OfflineEncountersDataSource
    ): OfflineEncountersDataSourceProvider

    @Binds
    abstract fun bindOfflineLocationAreaDataSource(
        dataSource: OfflineLocationAreaDataSource
    ): OfflineLocationAreaDataSourceProvider
}
