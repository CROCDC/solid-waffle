package com.crocdc.datanetworking.model

import com.squareup.moshi.Json

data class EvolutionDetails(
    @Json(name = "min_level") val minLevel: Int?
)
