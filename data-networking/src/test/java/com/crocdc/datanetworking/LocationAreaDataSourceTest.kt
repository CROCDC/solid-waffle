package com.crocdc.datanetworking

import com.crocdc.datanetworking.datasource.LocationAreaDataSource
import com.crocdc.datanetworking.di.NetworkModule
import org.junit.Test

class LocationAreaDataSourceTest {

    private val dataSource: LocationAreaDataSource = LocationAreaDataSource(
        NetworkModule.provideOkhttp(),
        NetworkModule.provideMoshi()
    )

    @Test
    fun getLocationArea() {
        dataSource.getLocationArea("281").assert()
    }
}