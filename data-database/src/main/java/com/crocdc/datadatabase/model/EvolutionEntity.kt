package com.crocdc.datadatabase.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EvolutionEntity(
    @PrimaryKey val name: String,
    @Embedded val evolvesTo: EvolvesTo
)