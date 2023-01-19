package com.crocdc.datanetworking

import com.crocdc.datanetworking.datasource.PokemonSpeciesDataSource
import com.crocdc.datanetworking.di.NetworkModule
import org.junit.Test

class PokemonSpeciesDataSourceTest {

    private val dataSource: PokemonSpeciesDataSource = PokemonSpeciesDataSource(
        NetworkModule.provideOkhttp(),
        NetworkModule.provideMoshi()
    )

    @Test
    fun getPokemonSpecies() {
        dataSource.getPokemonSpecies("squirtle").assert()
    }
}