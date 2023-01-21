package com.crocdc.domain.model

class PokemonInfo(
    val name: String,
    val officialArtWork: String,
    val officialArtWorkShiny: String,
    val types: List<Type>,
    val pokemonMoves: List<PokemonMove>,
    val abilities: List<Ability>,
    val sprite: String
)
