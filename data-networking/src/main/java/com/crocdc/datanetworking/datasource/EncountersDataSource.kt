package com.crocdc.datanetworking.datasource

import com.crocdc.datanetworking.BuildConfig
import com.crocdc.datanetworking.execute
import com.crocdc.modelnetworking.Encounter
import com.crocdc.modelnetworking.Resource
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import javax.inject.Inject

class EncountersDataSource @Inject constructor(
    private val okHttpClient: OkHttpClient,
    private val moshi: Moshi,
) : EncountersDataSourceProvider {

    override fun getEncounters(name: String): Resource<List<Encounter>> =
        okHttpClient.execute(
            moshi,
            builder().addPathSegment(name).addPathSegment("encounters"),
            Types.newParameterizedType(List::class.java, Encounter::class.java)
        )

    private fun builder() = HttpUrl.Builder()
        .scheme("https").host(BuildConfig.URL_API)
        .addPathSegment("api")
        .addPathSegment("v2")
        .addPathSegment("pokemon")
}
