package com.crocdc.datanetworking.model

import com.squareup.moshi.Json

data class Other(
    @Json(name = "official-artwork") val officialArtwork: OfficialArtwork
)
