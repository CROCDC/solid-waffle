package com.crocdc.datanetworking.datasource

import com.crocdc.datanetworking.BuildConfig
import com.crocdc.datanetworking.Resource
import com.crocdc.datanetworking.model.PokemonsResponse
import com.squareup.moshi.Moshi
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request

class PokemonDataSource(
    private val okHttpClient: OkHttpClient,
    private val moshi: Moshi
) {

    fun getPokemons(): Resource<PokemonsResponse> {
        val request = HttpUrl.Builder()
            .scheme("https")
            .host(BuildConfig.URL_API)
            .addPathSegment("api")
            .addPathSegment("v2")
            .addPathSegment("pokemon")
            .addQueryParameter("limit", "151")
            .build()

        val response = okHttpClient.newCall(Request.Builder().url(request).build()).execute()
        val json = response.body?.source()
        return if (response.code == 200 && json != null) {
            Resource.success(moshi.adapter(PokemonsResponse::class.java).fromJson(json))
        } else {
            Resource.error()
        }
    }
}