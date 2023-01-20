package com.crocdc.datanetworking.datasource

import com.crocdc.datanetworking.BuildConfig
import com.crocdc.datanetworking.Resource
import com.crocdc.datanetworking.model.Encounter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
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

    override fun getEncounters(name: String): Resource<List<Encounter>> {
        request.addPathSegment(name).addPathSegment("encounters")

        val response =
            okHttpClient.newCall(Request.Builder().url(request.build()).build()).execute()
        val json = response.body?.source()
        return if (response.code == 200 && json != null) {
            Resource.success(
                moshi.adapter<List<Encounter>>(
                    Types.newParameterizedType(
                        List::class.java,
                        Encounter::class.java
                    )
                ).fromJson(json)
            )
        } else {
            Resource.error()
        }
    }
}