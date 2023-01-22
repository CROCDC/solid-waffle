package com.crocdc.datadatabase.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocationAreaEntity(
    @PrimaryKey val id: String,
    val name: String,
    val pokemonEncounters: List<PokemonEncounter>
)