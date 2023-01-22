package com.crocdc.modelnetworking

import com.squareup.moshi.Json

data class PokemonsSpeciesResponse(
    @Json(name = "evolution_chain") val evolutionChain: EvolutionChain
)
