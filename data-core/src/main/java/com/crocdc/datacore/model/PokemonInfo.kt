package com.crocdc.datacore.model


class PokemonInfo(
    val name: String,
    val types: List<Type>,
    val moves: List<Move>,
    val abilities: List<Ability>,
    val locationAreaEncounters: String,
)