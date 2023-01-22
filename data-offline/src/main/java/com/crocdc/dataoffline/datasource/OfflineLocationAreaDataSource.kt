package com.crocdc.dataoffline.datasource

import android.content.res.AssetManager
import com.crocdc.dataoffline.openJson
import com.crocdc.modelnetworking.LocationAreaResponse
import com.squareup.moshi.Moshi
import javax.inject.Inject

class OfflineLocationAreaDataSource @Inject constructor(
    private val assetManager: AssetManager,
    private val moshi: Moshi,
) : OfflineLocationAreaDataSourceProvider {

    override fun getLocationArea(locationArea: String): LocationAreaResponse? =
        assetManager.openJson("location-area-$locationArea.json")?.let {
            moshi.adapter(LocationAreaResponse::class.java).fromJson(it)
        }
}
