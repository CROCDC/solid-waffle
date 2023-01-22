package com.crocdc.modelnetworking

import com.squareup.moshi.Json

data class EvolutionDetails(
    @Json(name = "min_level") val minLevel: Int?
)
