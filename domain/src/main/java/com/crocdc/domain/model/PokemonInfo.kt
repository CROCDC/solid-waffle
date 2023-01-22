package com.crocdc.domain.model

class PokemonInfo(
    val name: String,
    val officialArtWork: String,
    val officialArtWorkShiny: String,
    val types: List<Type>,
    val abilities: List<Ability>,
    val pokemonMoves: List<PokemonMove>,
)
