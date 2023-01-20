package com.crocdc.datadatabase.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class PokemonSpecieEntity(
    @PrimaryKey val name: String,
    val evolutionChain: String
)