package com.crocdc.datanetworking.datasource

import com.crocdc.datanetworking.BuildConfig
import com.crocdc.datanetworking.execute
import com.crocdc.modelnetworking.EvolutionResponse
import com.crocdc.modelnetworking.Resource
import com.squareup.moshi.Moshi
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
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

    override fun getEvolutions(evolutionChain: String): Resource<EvolutionResponse> =
        okHttpClient.execute(
            moshi, request.addPathSegment(evolutionChain)
        )
}
