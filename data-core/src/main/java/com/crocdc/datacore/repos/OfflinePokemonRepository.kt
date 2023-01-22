package com.crocdc.datacore.repos

import com.crocdc.datacore.networkBoundResource
import com.crocdc.datacore.offlineBoundResource
import com.crocdc.datadatabase.dao.PokemonDao
import com.crocdc.datadatabase.dao.PokemonInfoDao
import com.crocdc.datadatabase.model.Ability
import com.crocdc.datadatabase.model.LearnedAt
import com.crocdc.datadatabase.model.Move
import com.crocdc.datadatabase.model.PokemonEntity
import com.crocdc.datadatabase.model.PokemonInfoEntity
import com.crocdc.datadatabase.model.Type
import com.crocdc.datanetworking.datasource.PokemonDataSourceProvider
import com.crocdc.dataoffline.OfflinePokemonDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfflinePokemonRepository @Inject constructor(
    private val pokemonDao: PokemonDao,
    private val dataSource: OfflinePokemonDataSource
) {

    fun getPokemonsListing(query: String?): Flow<List<PokemonEntity>> = offlineBoundResource(
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
                r?.results?.map { listing ->
                    PokemonEntity(
                        listing.urlToId(),
                        listing.name
                    )
                }.orEmpty()
            )
        }
    )
}