package com.crocdc.datacore.repos

import com.crocdc.datacore.mapper.PokemonInfoEntityMapper
import com.crocdc.datacore.networkBoundResource
import com.crocdc.datadatabase.dao.PokemonDao
import com.crocdc.datadatabase.dao.PokemonInfoDao
import com.crocdc.datadatabase.model.PokemonEntity
import com.crocdc.datadatabase.model.PokemonInfoEntity
import com.crocdc.datanetworking.datasource.PokemonDataSourceProvider
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val pokemonDao: PokemonDao,
    private val pokemonInfoDao: PokemonInfoDao,
    private val dataSource: PokemonDataSourceProvider
) {

    fun getPokemonsListing(query: String?): Flow<List<PokemonEntity>> = networkBoundResource(
        query = {
            if (query == null) {
                pokemonDao.getAll()
            } else {
                pokemonDao.search(query)
            }
        },
        fetch = {
            dataSource.getPokemonsListing()
        },
        saveFetchResult = { r ->
            pokemonDao.deleteAll()
            pokemonDao.saveAll(
                r.data?.results?.map { listing ->
                    PokemonEntity(
                        listing.urlToId(),
                        listing.name
                    )
                }.orEmpty()
            )
        },
        shouldFetch = {
            // TODO
            it.size < 3
        }
    )

    fun getPokemonInfo(name: String): Flow<PokemonInfoEntity?> = networkBoundResource(
        query = {
            pokemonInfoDao.getPokemonInfoEntity(name)
        },
        fetch = {
            dataSource.getPokemonInfo(name)
        },
        saveFetchResult = { r ->
            r.data?.let {
                pokemonInfoDao.save(PokemonInfoEntityMapper.transform(it))
            }
        }
    )
}
