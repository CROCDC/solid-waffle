package com.crocdc.datadatabase.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EncountersEntity(
    @PrimaryKey val name: String,
    val encounters: List<Encounter>
)
