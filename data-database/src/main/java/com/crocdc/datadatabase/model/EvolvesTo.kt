package com.crocdc.datadatabase.model

import androidx.room.Embedded

data class EvolvesTo(
    val minLevel: Int?,
    @Embedded(prefix = "pokemon_evolution") val pokemonEvolution: PokemonEvolution,
    @Embedded(prefix = "evolves_2") val evolvesTo: EvolvesTo2?
)
