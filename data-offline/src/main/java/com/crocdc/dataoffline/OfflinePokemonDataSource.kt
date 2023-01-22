package com.crocdc.dataoffline

import android.content.res.AssetManager
import com.crocdc.modelnetworking.PokemonInfo
import com.crocdc.modelnetworking.PokemonsResponse
import com.crocdc.modelnetworking.Resource
import com.squareup.moshi.Moshi
import javax.inject.Inject

class OfflinePokemonDataSource @Inject constructor(
    private val moshi: Moshi
) : OfflinePokemonDataSourceProvider {

    override fun getPokemonsListing(): PokemonsResponse? =
        moshi.adapter(PokemonsResponse::class.java).fromJson(jsons.pokemons)

    override fun getPokemonInfo(name: String): PokemonInfo? =
        moshi.adapter(PokemonInfo::class.java).fromJson(jsons.pokemons)
}
