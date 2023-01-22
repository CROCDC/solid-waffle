package com.crocdc.dataoffline.datasource

import com.crocdc.modelnetworking.LocationAreaResponse

interface OfflineLocationAreaDataSourceProvider {
    fun getLocationArea(locationArea: String): LocationAreaResponse?
}
