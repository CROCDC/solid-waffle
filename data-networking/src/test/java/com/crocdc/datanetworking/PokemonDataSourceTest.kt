package com.crocdc.datanetworking

import com.crocdc.datanetworking.datasource.PokemonDataSource
import com.crocdc.datanetworking.datasource.PokemonDataSourceProvider
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import org.junit.Assert.assertEquals
import org.junit.Test

class PokemonDataSourceTest {

    private val dataSource: PokemonDataSourceProvider = PokemonDataSource(
        OkHttpClient(),
        Moshi.Builder().build()
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