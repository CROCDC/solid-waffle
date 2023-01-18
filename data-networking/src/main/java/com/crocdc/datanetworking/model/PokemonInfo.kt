package com.crocdc.datanetworking.model

import com.squareup.moshi.Json

data class PokemonInfo(
    val types: List<Types>,
    val moves: List<Moves>,
    val abilities: List<Abilities>,
    @Json(name = "location_area_encounters")
    val locationAreaEncounters: String,
    val name: String,
    val sprites: Sprites
)