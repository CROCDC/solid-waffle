package com.crocdc.datadatabase.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonInfoEntity(
    @PrimaryKey val name: String,
    val types: List<Type>,
    val moves: List<Move>,
    val abilities: List<Ability>,
    val locationAreaEncounters: String,
    val officialArtWork: String,
    val officialArtWorkShiny: String,
    val sprite: String
)
