package com.crocdc.dataoffline.datasource

import com.crocdc.modelnetworking.EvolutionResponse

interface OfflineEvolutionsDataSourceProvider {
    fun getEvolutions(evolutionChain: String): EvolutionResponse?
}
