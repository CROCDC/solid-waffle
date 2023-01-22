package com.crocdc.datanetworking.datasource

import com.crocdc.datanetworking.BuildConfig
import com.crocdc.modelnetworking.Resource
import com.crocdc.modelnetworking.EvolutionResponse
import com.squareup.moshi.Moshi
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Inject

class EvolutionsDataSource @Inject constructor(
    private val okHttpClient: OkHttpClient,
    private val moshi: Moshi,
) : EvolutionsDataSourceProvider {

    private val request: HttpUrl.Builder = HttpUrl.Builder()
        .scheme("https").host(BuildConfig.URL_API)
        .addPathSegment("api")
        .addPathSegment("v2")
        .addPathSegment("evolution-chain")

    override fun getEvolutions(evolutionChain: String): Resource<EvolutionResponse> {
        request.addPathSegment(evolutionChain)

        val response =
            okHttpClient.newCall(Request.Builder().url(request.build()).build()).execute()
        val json = response.body?.source()
        return if (response.code == 200 && json != null) {
            Resource.success(
                moshi.adapter(EvolutionResponse::class.java)
                    .fromJson(json)
            )
        } else {
            Resource.error()
        }
    }
}