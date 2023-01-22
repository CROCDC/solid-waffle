package com.crocdc.datanetworking.datasource

import com.crocdc.modelnetworking.Resource
import com.crocdc.modelnetworking.PokemonInfo
import com.crocdc.modelnetworking.PokemonsResponse

interface PokemonDataSourceProvider {
    fun getPokemonsListing(): Resource<PokemonsResponse>
    fun getPokemonInfo(name: String): Resource<PokemonInfo>
}
