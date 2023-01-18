package com.crocdc.datanetworking

import com.crocdc.datanetworking.datasource.EvolutionsDataSource
import com.crocdc.datanetworking.di.NetworkModule
import org.junit.Test

class EvolutionsDataSourceTest {

    private val dataSource: EvolutionsDataSource = EvolutionsDataSource(
        NetworkModule.provideOkhttp(),
        NetworkModule.provideMoshi()
    )

    @Test
    fun getPokemonsListing() {
        dataSource.getEvolutions("7").assert()
    }
}
