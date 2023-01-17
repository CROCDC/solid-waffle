package com.crocdc.datanetworking

import com.crocdc.datanetworking.datasource.PokemonDataSource
import com.crocdc.datanetworking.datasource.PokemonDataSourceProvider
import com.crocdc.datanetworking.di.NetworkModule
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import org.junit.Assert.assertEquals
import org.junit.Test

class PokemonDataSourceTest {

    private val dataSource: PokemonDataSourceProvider = PokemonDataSource(
        NetworkModule.provideOkhttp(),
        NetworkModule.provideMoshi()
    )

    @Test
    fun getPokemonsListing() {
        dataSource.getPokemonsListing().assert()
    }

    private fun <T> Resource<T>.assert() {
        if (status != StatusResponse.LOADING) {
            assertEquals(StatusResponse.SUCCESS, status)
            println(data)
        }
    }
}