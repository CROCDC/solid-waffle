package com.crocdc.datacore.repos

import com.crocdc.datacore.model.Pokemon
import com.crocdc.datacore.networkBoundResource
import com.crocdc.datadatabase.dao.PokemonDao
import com.crocdc.datadatabase.model.PokemonEntity
import com.crocdc.datanetworking.datasource.PokemonDataSourceProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val dao: PokemonDao,
    private val dataSource: PokemonDataSourceProvider
) {

    fun getPokemonsListing(): Flow<List<Pokemon>> = networkBoundResource(
        query = {
            // todo improve image get
            dao.getAll().map { pokemonEntities ->
                pokemonEntities.map {
                    Pokemon(
                        it.name,
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${it.id}.png"
                    )
                }
            }
        },
        fetch = {
            dataSource.getPokemonsListing()
        },
        saveFetchResult = { r ->
            //todo improve id
            dao.saveAll(
                r.data?.results?.map {
                    PokemonEntity(
                        it.url.last().toString(),
                        it.name
                    )
                }.orEmpty()
            )
        },
        shouldFetch = { it.isEmpty() }
    )
}