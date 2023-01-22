package com.crocdc.modelnetworking

import com.squareup.moshi.Json

data class Chain(
    @Json(name = "evolves_to") val evolvesTo: List<EvolvesTo>,
    val species: NamedApiResource
)
