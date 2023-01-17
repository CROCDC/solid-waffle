package com.crocdc.datadatabase.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonEntity(
    @PrimaryKey val id: String,
    val name: String
)
