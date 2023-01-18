package com.crocdc.datanetworking.datasource

import com.crocdc.datanetworking.Resource
import com.crocdc.datanetworking.model.PokemonInfo
import com.crocdc.datanetworking.model.PokemonsResponse

interface PokemonDataSourceProvider {
    fun getPokemonsListing(): Resource<PokemonsResponse>
    fun getPokemonInfo(id: String): Resource<PokemonInfo>
}
