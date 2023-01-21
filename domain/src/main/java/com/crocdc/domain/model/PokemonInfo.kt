package com.crocdc.domain.model

class PokemonInfo(
    val name: String,
    val image: String,
    val types: List<Type>,
    val pokemonMoves: List<PokemonMove>,
    val abilities: List<Ability>,
    val sprite: String
)
