package com.crocdc.datadatabase.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EvolutionEntity(
    @PrimaryKey val chain: String,
    @Embedded val basePokemon: PokemonEvolution,
    @Embedded val evolvesTo: EvolvesTo
)
