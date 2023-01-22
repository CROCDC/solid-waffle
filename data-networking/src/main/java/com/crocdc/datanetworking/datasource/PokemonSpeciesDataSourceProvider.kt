package com.crocdc.datanetworking.datasource

import com.crocdc.modelnetworking.Resource
import com.crocdc.modelnetworking.PokemonsSpeciesResponse

interface PokemonSpeciesDataSourceProvider {
    fun getPokemonSpecies(name: String): Resource<PokemonsSpeciesResponse>
}
