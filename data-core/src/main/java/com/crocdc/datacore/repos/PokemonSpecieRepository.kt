package com.crocdc.datacore.repos

import com.crocdc.datacore.networkBoundResource
import com.crocdc.datadatabase.dao.PokemonSpecieDao
import com.crocdc.datadatabase.model.PokemonSpecieEntity
import com.crocdc.datanetworking.datasource.PokemonSpeciesDataSourceProvider
import javax.inject.Inject

class PokemonSpecieRepository @Inject constructor(
    private val dao: PokemonSpecieDao,
    private val dataSource: PokemonSpeciesDataSourceProvider
) {
    fun getPokemonSpecie(name: String) = networkBoundResource(
        query = {
            dao.getPokemonSpecie(name)
        },
        fetch = {
            dataSource.getPokemonSpecies(name)
        },
        saveFetchResult = {
            it.data?.let {
                dao.save(PokemonSpecieEntity(name, it.evolutionChain.urlToId()))
            }
        },
        shouldFetch = {
            it == null
        }
    )
}
