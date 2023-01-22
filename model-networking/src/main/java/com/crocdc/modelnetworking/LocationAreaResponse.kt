package com.crocdc.modelnetworking

import com.squareup.moshi.Json

data class LocationAreaResponse(
    val names: List<Name>,
    @Json(name = "pokemon_encounters") val pokemonEncounters: List<PokemonEncounter>
)
