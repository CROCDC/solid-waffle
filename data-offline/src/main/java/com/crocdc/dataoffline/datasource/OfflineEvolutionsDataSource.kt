package com.crocdc.dataoffline.datasource

import android.content.res.AssetManager
import com.crocdc.dataoffline.openJson
import com.crocdc.modelnetworking.EvolutionResponse
import com.squareup.moshi.Moshi
import javax.inject.Inject

class OfflineEvolutionsDataSource @Inject constructor(
    private val moshi: Moshi, private val assetManager: AssetManager
) : OfflineEvolutionsDataSourceProvider {

    override fun getEvolutions(evolutionChain: String): EvolutionResponse? =
        assetManager.openJson("evolutions-$evolutionChain.json")?.let {
            moshi.adapter(EvolutionResponse::class.java).fromJson(it)
        }
}
