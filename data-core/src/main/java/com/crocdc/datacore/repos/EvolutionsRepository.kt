package com.crocdc.datacore.repos

import com.crocdc.datacore.mapper.EvolutionEntityMapper
import com.crocdc.datacore.networkBoundResource
import com.crocdc.datadatabase.dao.EvolutionDao
import com.crocdc.datanetworking.datasource.EvolutionsDataSourceProvider
import javax.inject.Inject

class EvolutionsRepository @Inject constructor(
    private val dao: EvolutionDao,
    private val dataSource: EvolutionsDataSourceProvider
) {

    fun getEvolutions(evolutionChain: String) = networkBoundResource(
        query = { dao.getEvolutionEntity(evolutionChain) },
        fetch = { dataSource.getEvolutions(evolutionChain) },
        saveFetchResult = { r ->
            r.data?.let { response ->
                EvolutionEntityMapper(evolutionChain).transform(response)?.let {
                    dao.save(it)
                }
            }
        },
        shouldFetch = { it == null }
    )
}
