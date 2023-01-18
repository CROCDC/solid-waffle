package com.crocdc.datanetworking.datasource

import com.crocdc.datanetworking.BuildConfig
import com.crocdc.datanetworking.Resource
import com.crocdc.datanetworking.model.PokemonInfo
import com.crocdc.datanetworking.model.PokemonsResponse
import com.squareup.moshi.Moshi
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Inject

class PokemonDataSource @Inject constructor(
    private val okHttpClient: OkHttpClient, private val moshi: Moshi
) : PokemonDataSourceProvider {

    private val request: HttpUrl.Builder = HttpUrl.Builder()
        .scheme("https").host(BuildConfig.URL_API)
        .addPathSegment("api")
        .addPathSegment("v2")
        .addPathSegment("pokemon")

    override fun getPokemonsListing(): Resource<PokemonsResponse> {
        request.addQueryParameter("limit", "151")

        val response =
            okHttpClient.newCall(Request.Builder().url(request.build()).build()).execute()
        val json = response.body?.source()
        return if (response.code == 200 && json != null) {
            Resource.success(moshi.adapter(PokemonsResponse::class.java).fromJson(json))
        } else {
            Resource.error()
        }
    }

    override fun getPokemonInfo(name: String): Resource<PokemonInfo> {
        request.addPathSegment(name)

        val response =
            okHttpClient.newCall(Request.Builder().url(request.build()).build()).execute()
        val json = response.body?.source()
        return if (response.code == 200 && json != null) {
            Resource.success(moshi.adapter(PokemonInfo::class.java).fromJson(json))
        } else {
            Resource.error()
        }
    }
}
