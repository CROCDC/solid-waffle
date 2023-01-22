package com.crocdc.modelnetworking

import com.squareup.moshi.Json

data class Other(
    @Json(name = "official-artwork") val officialArtwork: OfficialArtwork
)
