package com.crocdc.dataoffline

import android.content.res.AssetManager
import com.crocdc.modelnetworking.PokemonInfo
import com.crocdc.modelnetworking.PokemonsResponse
import com.squareup.moshi.Moshi
import java.io.IOException
import javax.inject.Inject

class OfflinePokemonDataSource @Inject constructor(
    private val moshi: Moshi,
    private val assetManager: AssetManager
) : OfflinePokemonDataSourceProvider {

    override fun getPokemonsListing(): PokemonsResponse? =
        assetManager.openJson("pokemons.json")?.let {
            moshi.adapter(PokemonsResponse::class.java).fromJson(it)
        }

    override fun getPokemonInfo(name: String): PokemonInfo? =
        assetManager.openJson("$name.json")?.let {
            moshi.adapter(PokemonInfo::class.java).fromJson(it)
        }
}
