package com.crocdc.datacore.repos

import com.crocdc.datacore.mapper.PokemonInfoEntityMapper
import com.crocdc.datacore.offlineBoundResource
import com.crocdc.datadatabase.dao.PokemonDao
import com.crocdc.datadatabase.dao.PokemonInfoDao
import com.crocdc.datadatabase.model.PokemonEntity
import com.crocdc.datadatabase.model.PokemonInfoEntity
import com.crocdc.dataoffline.datasource.OfflinePokemonDataSourceProvider
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfflinePokemonRepository @Inject constructor(
    private val pokemonDao: PokemonDao,
    private val pokemonInfo: PokemonInfoDao,
    private val dataSource: OfflinePokemonDataSourceProvider
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

    fun getPokemonInfo(name: String): Flow<PokemonInfoEntity?> = offlineBoundResource(
        query = {
            pokemonInfo.getPokemonInfoEntity(name)
        },
        fetch = {
            dataSource.getPokemonInfo(name)
        },
        saveFetchResult = { r ->
            r?.let {
                pokemonInfo.save(
                    PokemonInfoEntityMapper.transform(it)
                )
            }
        }
    )
}
