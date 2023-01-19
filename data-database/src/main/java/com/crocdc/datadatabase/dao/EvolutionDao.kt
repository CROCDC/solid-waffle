package com.crocdc.datadatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.crocdc.datadatabase.model.EvolutionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EvolutionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(evolutionEntity: EvolutionEntity)

    @Query("SELECT * FROM EvolutionEntity WHERE name == :name")
    fun getEvolutionEntity(name: String): Flow<EvolutionEntity?>
}