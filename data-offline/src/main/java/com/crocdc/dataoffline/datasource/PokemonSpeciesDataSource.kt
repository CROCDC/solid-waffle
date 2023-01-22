package com.crocdc.dataoffline.datasource

import android.content.res.AssetManager
import com.crocdc.dataoffline.openJson
import com.crocdc.modelnetworking.PokemonsSpeciesResponse
import com.squareup.moshi.Moshi
import javax.inject.Inject

class OfflinePokemonSpeciesDataSource @Inject constructor(
    private val moshi: Moshi,
    private val assetManager: AssetManager
) : OfflinePokemonSpeciesDataSourceProvider {

    override fun getPokemonSpecies(name: String): PokemonsSpeciesResponse? =
        assetManager.openJson("species-$name.json")?.let {
            moshi.adapter(PokemonsSpeciesResponse::class.java).fromJson(it)
        }
}