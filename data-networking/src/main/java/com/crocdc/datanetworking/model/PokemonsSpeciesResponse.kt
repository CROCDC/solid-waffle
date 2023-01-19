package com.crocdc.datanetworking.model

import com.squareup.moshi.Json

data class PokemonsSpeciesResponse(
    @Json(name = "evolution_chain") val evolutionChain: EvolutionChain
)