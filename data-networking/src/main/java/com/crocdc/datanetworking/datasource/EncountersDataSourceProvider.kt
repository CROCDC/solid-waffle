package com.crocdc.datanetworking.datasource

import com.crocdc.datanetworking.Resource
import com.crocdc.datanetworking.model.Encounter

interface EncountersDataSourceProvider {
    fun getEncounters(name: String): Resource<List<Encounter>>
}
