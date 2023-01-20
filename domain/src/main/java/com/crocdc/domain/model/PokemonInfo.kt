package com.crocdc.domain.model

class PokemonInfo(
    val name: String,
    val image: String,
    val types: List<Type>,
    val moves: List<Move>,
    val abilities: List<Ability>,
    val sprite: String
)
