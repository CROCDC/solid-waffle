package com.crocdc.dataoffline.datasource

import com.crocdc.modelnetworking.Encounter

interface OfflineEncountersDataSourceProvider {
    fun getEncounters(name: String): List<Encounter>
}