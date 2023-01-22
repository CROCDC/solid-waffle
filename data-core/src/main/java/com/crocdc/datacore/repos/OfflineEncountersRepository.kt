package com.crocdc.datacore.repos

import com.crocdc.datacore.offlineBoundResource
import com.crocdc.datadatabase.dao.EncountersDao
import com.crocdc.datadatabase.model.Encounter
import com.crocdc.datadatabase.model.EncountersEntity
import com.crocdc.dataoffline.datasource.OfflineEncountersDataSourceProvider
import javax.inject.Inject

class OfflineEncountersRepository @Inject constructor(
    private val dao: EncountersDao,
    private val dataSource: OfflineEncountersDataSourceProvider
) {

    fun getEncounters(name: String) = offlineBoundResource(
        query = { dao.getEncountersEntity(name) },
        fetch = { dataSource.getEncounters(name) },
        saveFetchResult = { encounters ->
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
    )
}
