package com.crocdc.dataoffline.datasource

import android.content.res.AssetManager
import com.crocdc.dataoffline.openJson
import com.crocdc.modelnetworking.Encounter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import javax.inject.Inject

class OfflineEncountersDataSource @Inject constructor(
    private val moshi: Moshi,
    private val assetManager: AssetManager
) : OfflineEncountersDataSourceProvider {

    override fun getEncounters(name: String): List<Encounter> =
        assetManager.openJson("encounters-$name.json")?.let {
            moshi.adapter<List<Encounter>>(
                Types.newParameterizedType(
                    List::class.java,
                    Encounter::class.java
                )
            ).fromJson(it)
        } ?: emptyList()
}
