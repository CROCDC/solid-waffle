package com.crocdc.dataoffline.datasource

import com.crocdc.modelnetworking.PokemonInfo
import com.crocdc.modelnetworking.PokemonsResponse

interface OfflinePokemonDataSourceProvider {
    fun getPokemonsListing(): PokemonsResponse?
    fun getPokemonInfo(name: String): PokemonInfo?
}
