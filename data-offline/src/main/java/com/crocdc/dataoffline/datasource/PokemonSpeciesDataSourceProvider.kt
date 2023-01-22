package com.crocdc.dataoffline.datasource

import com.crocdc.modelnetworking.Resource
import com.crocdc.modelnetworking.PokemonsSpeciesResponse

interface OfflinePokemonSpeciesDataSourceProvider {
    fun getPokemonSpecies(name: String): PokemonsSpeciesResponse?
}
