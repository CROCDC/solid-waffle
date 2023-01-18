package com.crocdc.datanetworking.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonInfo(
    val types: List<Types>,
    val moves: List<Moves>,
    val abilities: List<Abilities>,
    @Json(name = "location_area_encounters")
    val locationAreaEncounters: String,
    val name: String
)