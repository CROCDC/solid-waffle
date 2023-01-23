package com.crocdc.datanetworking.datasource

import com.crocdc.datanetworking.BuildConfig
import com.crocdc.datanetworking.execute
import com.crocdc.modelnetworking.PokemonInfo
import com.crocdc.modelnetworking.PokemonsResponse
import com.crocdc.modelnetworking.Resource
import com.squareup.moshi.Moshi
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import javax.inject.Inject

class PokemonDataSource @Inject constructor(
    private val okHttpClient: OkHttpClient,
    private val moshi: Moshi
) : PokemonDataSourceProvider {

    fun builder() = HttpUrl.Builder()
        .scheme("https").host(BuildConfig.URL_API)
        .addPathSegment("api")
        .addPathSegment("v2")
        .addPathSegment("pokemon")

    override fun getPokemonsListing(): Resource<PokemonsResponse> =
        okHttpClient.execute(
            moshi,
            builder().addQueryParameter("limit", "151")
        )

    override fun getPokemonInfo(name: String): Resource<PokemonInfo> =
        okHttpClient.execute(
            moshi,
            builder().addPathSegment(name)
        )
}
