package com.crocdc.datanetworking.datasource

import com.crocdc.modelnetworking.Resource
import com.crocdc.modelnetworking.Encounter

interface EncountersDataSourceProvider {
    fun getEncounters(name: String): Resource<List<Encounter>>
}
