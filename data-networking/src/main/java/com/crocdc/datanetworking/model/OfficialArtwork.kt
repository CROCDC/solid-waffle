package com.crocdc.datanetworking.model

import com.squareup.moshi.Json

data class OfficialArtwork(
    @Json(name = "front_default") val frontDefault: String,
    @Json(name = "front_shiny") val frontShiny: String
)
