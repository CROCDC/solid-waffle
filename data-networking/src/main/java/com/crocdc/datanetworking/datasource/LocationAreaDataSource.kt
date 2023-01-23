package com.crocdc.datanetworking.datasource

import com.crocdc.datanetworking.BuildConfig
import com.crocdc.datanetworking.execute
import com.crocdc.modelnetworking.LocationAreaResponse
import com.crocdc.modelnetworking.Resource
import com.squareup.moshi.Moshi
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import javax.inject.Inject

class LocationAreaDataSource @Inject constructor(
    private val okHttpClient: OkHttpClient,
    private val moshi: Moshi,
) : LocationAreaDataSourceProvider {

    fun builder() = HttpUrl.Builder()
        .scheme("https").host(BuildConfig.URL_API)
        .addPathSegment("api")
        .addPathSegment("v2")
        .addPathSegment("location-area")

    override fun getLocationArea(locationArea: String): Resource<LocationAreaResponse> =
        okHttpClient.execute(
            moshi, builder().addPathSegment(locationArea)
        )
}
