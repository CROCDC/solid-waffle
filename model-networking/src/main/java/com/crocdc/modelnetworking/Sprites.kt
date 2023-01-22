package com.crocdc.modelnetworking

import com.squareup.moshi.Json

data class Sprites(
    val other: Other,
    @Json(name = "front_default") val frontDefault: String
)
