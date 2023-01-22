package com.crocdc.datanetworking

import com.crocdc.datanetworking.datasource.PokemonDataSource
import com.crocdc.datanetworking.datasource.PokemonDataSourceProvider
import com.crocdc.datanetworking.di.NetworkModule
import org.junit.Assert.assertEquals
import org.junit.Test

class PokemonDataSourceTest {

    private val dataSource: PokemonDataSourceProvider = PokemonDataSource(
        NetworkModule.provideOkhttp(),
        NetworkModule.provideMoshi()
    )

    @Test
    fun getPokemonsListing() {
        val resource = dataSource.getPokemonsListing()
        resource.assert()
        assertEquals(151, resource.data?.results?.size)
    }

    @Test
    fun getPokemonInfo() {
        dataSource.getPokemonInfo("7").assert()
    }
}
