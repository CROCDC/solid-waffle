package com.crocdc.datanetworking.datasource

import com.crocdc.datanetworking.Resource

interface EvolutionsDataSourceProvider {
    fun getEvolutions(name: String): Resource<EvolutionResponse>
}
