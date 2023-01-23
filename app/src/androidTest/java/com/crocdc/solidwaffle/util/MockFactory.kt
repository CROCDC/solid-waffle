package com.crocdc.solidwaffle.util

import com.crocdc.domain.model.Ability
import com.crocdc.domain.model.FromEvolutionTo
import com.crocdc.domain.model.Pokemon
import com.crocdc.domain.model.PokemonMove

object MockFactory {
    val areaName: String = "Area"
    val pokemonName = "Squirtle"
    val image = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/7.png"
    val fromEvolutionTo = FromEvolutionTo(
        Pokemon(
            "Squirtle",
            image
        ),
        12,
        Pokemon(
            "wartortle",
            image
        )
    )
    val move = PokemonMove("Tackle")

    val ability = Ability("fire", false)
}