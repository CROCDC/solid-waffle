package com.crocdc.datanetworking

import com.crocdc.datanetworking.datasource.EncountersDataSource
import com.crocdc.datanetworking.datasource.EncountersDataSourceProvider
import com.crocdc.datanetworking.di.NetworkModule
import org.junit.Test

class EncountersDataSourceTest {

    private val dataSource: EncountersDataSourceProvider = EncountersDataSource(
        NetworkModule.provideOkhttp(),
        NetworkModule.provideMoshi()
    )

    @Test
    fun getEncounters() {
        dataSource.getEncounters("squirtle").assert()
    }
}
