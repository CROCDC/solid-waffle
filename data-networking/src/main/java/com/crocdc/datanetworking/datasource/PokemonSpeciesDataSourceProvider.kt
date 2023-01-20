package com.crocdc.datanetworking.datasource

import com.crocdc.datanetworking.Resource
import com.crocdc.datanetworking.model.PokemonsSpeciesResponse

interface PokemonSpeciesDataSourceProvider {
    fun getPokemonSpecies(name: String): Resource<PokemonsSpeciesResponse>
}
