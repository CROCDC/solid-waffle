package com.crocdc.datanetworking.model

import com.squareup.moshi.Json

data class EvolvesTo(
    @Json(name = "evolution_details") val evolutionDetails: List<EvolutionDetails>,
    @Json(name = "evolves_to") val evolvesTo: List<EvolvesTo>?,
    val species: Species
)
