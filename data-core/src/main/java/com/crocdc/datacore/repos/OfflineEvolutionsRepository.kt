package com.crocdc.datacore.repos

import com.crocdc.datacore.mapper.EvolutionEntityMapper
import com.crocdc.datacore.offlineBoundResource
import com.crocdc.datadatabase.dao.EvolutionDao
import com.crocdc.dataoffline.datasource.OfflineEvolutionsDataSourceProvider
import javax.inject.Inject

class OfflineEvolutionsRepository @Inject constructor(
    private val dao: EvolutionDao,
    private val dataSource: OfflineEvolutionsDataSourceProvider
) {

    fun getEvolutions(evolutionChain: String) = offlineBoundResource(
        query = { dao.getEvolutionEntity(evolutionChain) },
        fetch = { dataSource.getEvolutions(evolutionChain) },
        saveFetchResult = { r ->
            r?.let { response ->
                EvolutionEntityMapper(evolutionChain).transform(response)?.let { dao.save(it) }
            }
        }
    )
}
