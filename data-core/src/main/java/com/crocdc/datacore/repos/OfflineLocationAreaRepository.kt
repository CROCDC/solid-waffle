package com.crocdc.datacore.repos

import com.crocdc.datacore.mapper.LocationAreaMapper
import com.crocdc.datacore.networkBoundResource
import com.crocdc.datadatabase.dao.LocationAreaDao
import com.crocdc.dataoffline.datasource.OfflineLocationAreaDataSourceProvider
import javax.inject.Inject

class OfflineLocationAreaRepository @Inject constructor(
    private val dataSource: OfflineLocationAreaDataSourceProvider,
    private val dao: LocationAreaDao
) {

    fun getLocationArea(id: String) = networkBoundResource(
        query = { dao.getLocationAreaEntity(id) },
        fetch = { dataSource.getLocationArea(id) },
        saveFetchResult = {
            it?.let {
                dao.save(LocationAreaMapper(id).transform(it))
            }
        }
    )
}
