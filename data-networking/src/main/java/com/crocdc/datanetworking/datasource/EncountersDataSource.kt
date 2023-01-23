package com.crocdc.datanetworking.datasource

import com.crocdc.datanetworking.BuildConfig
import com.crocdc.datanetworking.execute
import com.crocdc.modelnetworking.Encounter
import com.crocdc.modelnetworking.Resource
import com.squareup.moshi.Moshi
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import javax.inject.Inject

class EncountersDataSource @Inject constructor(
    private val okHttpClient: OkHttpClient,
    private val moshi: Moshi,
) : EncountersDataSourceProvider {

    private val request: HttpUrl.Builder = HttpUrl.Builder()
        .scheme("https").host(BuildConfig.URL_API)
        .addPathSegment("api")
        .addPathSegment("v2")
        .addPathSegment("pokemon")

    override fun getEncounters(name: String): Resource<List<Encounter>> =
        okHttpClient.execute(
            moshi,
            request.addPathSegment(name).addPathSegment("encounters")
        )
}
