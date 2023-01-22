package com.crocdc.datanetworking.datasource

import com.crocdc.datanetworking.BuildConfig
import com.crocdc.modelnetworking.LocationAreaResponse
import com.crocdc.modelnetworking.Resource
import com.squareup.moshi.Moshi
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Inject

class LocationAreaDataSource @Inject constructor(
    private val okHttpClient: OkHttpClient,
    private val moshi: Moshi,
) : LocationAreaDataSourceProvider {

    private val request: HttpUrl.Builder = HttpUrl.Builder()
        .scheme("https").host(BuildConfig.URL_API)
        .addPathSegment("api")
        .addPathSegment("v2")
        .addPathSegment("location-area")

    override fun getLocationArea(locationArea: String): Resource<LocationAreaResponse> {
        request.addPathSegment(locationArea)

        val response =
            okHttpClient.newCall(Request.Builder().url(request.build()).build()).execute()
        val json = response.body?.source()
        return if (response.code == 200 && json != null) {
            Resource.success(
                moshi.adapter(LocationAreaResponse::class.java).fromJson(json)
            )
        } else {
            Resource.error()
        }
    }
}
