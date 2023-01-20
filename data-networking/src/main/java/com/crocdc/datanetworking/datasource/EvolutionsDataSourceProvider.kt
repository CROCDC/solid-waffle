package com.crocdc.datanetworking.datasource

import com.crocdc.datanetworking.Resource
import com.crocdc.datanetworking.model.EvolutionResponse

interface EvolutionsDataSourceProvider {
    fun getEvolutions(evolutionChain: String): Resource<EvolutionResponse>
}
