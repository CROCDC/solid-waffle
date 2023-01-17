package com.crocdc.datanetworking.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonListing(val name: String, val url: String)

