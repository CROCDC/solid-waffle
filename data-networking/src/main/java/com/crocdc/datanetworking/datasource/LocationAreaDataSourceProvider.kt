package com.crocdc.datanetworking.datasource

import com.crocdc.modelnetworking.LocationAreaResponse
import com.crocdc.modelnetworking.Resource

interface LocationAreaDataSourceProvider {

    fun getLocationArea(locationArea: String): Resource<LocationAreaResponse>
}
