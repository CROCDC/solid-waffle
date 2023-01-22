package com.crocdc.datacore.repos

import com.crocdc.datacore.mapper.LocationAreaMapper
import com.crocdc.datacore.networkBoundResource
import com.crocdc.datadatabase.dao.LocationAreaDao
import com.crocdc.datanetworking.datasource.LocationAreaDataSourceProvider
import javax.inject.Inject

class LocationAreaRepository @Inject constructor(
    private val dataSource: LocationAreaDataSourceProvider,
    private val dao: LocationAreaDao
) {

    fun getLocationArea(id: String) = networkBoundResource(
        query = { dao.getLocationAreaEntity(id) },
        fetch = { dataSource.getLocationArea(id) },
        saveFetchResult = {
            it.data?.let {
                dao.save(LocationAreaMapper(id).transform(it))
            }
        }
    )
}