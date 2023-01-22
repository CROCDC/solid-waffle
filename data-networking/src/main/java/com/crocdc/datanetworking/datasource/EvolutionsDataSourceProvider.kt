package com.crocdc.datanetworking.datasource

import com.crocdc.modelnetworking.Resource
import com.crocdc.modelnetworking.EvolutionResponse

interface EvolutionsDataSourceProvider {
    fun getEvolutions(evolutionChain: String): Resource<EvolutionResponse>
}
