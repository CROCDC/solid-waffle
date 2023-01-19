package com.crocdc.datadatabase.model

import androidx.room.Embedded

//TODO ?
class EvolvesTo2(
    val minLevel: Int,
    @Embedded val pokemonEvolution: PokemonEvolution,
)