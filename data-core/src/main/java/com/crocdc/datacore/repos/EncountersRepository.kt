package com.crocdc.datacore.repos

import com.crocdc.datacore.networkBoundResource
import com.crocdc.datadatabase.dao.EncountersDao
import com.crocdc.datadatabase.model.Encounter
import com.crocdc.datadatabase.model.EncountersEntity
import com.crocdc.datanetworking.datasource.EncountersDataSourceProvider
import javax.inject.Inject

class EncountersRepository @Inject constructor(
    private val dao: EncountersDao,
    private val dataSource: EncountersDataSourceProvider
) {

    fun getEncounters(name: String) = networkBoundResource(
        query = { dao.getEncountersEntity(name) },
        fetch = { dataSource.getEncounters(name) },
        saveFetchResult = { r ->
            r.data?.let { encounters ->
                dao.save(
                    EncountersEntity(
                        name,
                        encounters.map {
                            Encounter(
                                it.locationArea.name,
                                it.locationArea.urlToId()
                            )
                        }
                    )
                )
            }
        },
        shouldFetch = { it == null }
    )
}
