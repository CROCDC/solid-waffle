package com.crocdc.dataoffline

import com.crocdc.modelnetworking.PokemonInfo
import com.crocdc.modelnetworking.PokemonsResponse
import com.crocdc.modelnetworking.Resource

interface OfflinePokemonDataSourceProvider {

    fun getPokemonsListing(): PokemonsResponse?
    fun getPokemonInfo(name: String): PokemonInfo?
}
