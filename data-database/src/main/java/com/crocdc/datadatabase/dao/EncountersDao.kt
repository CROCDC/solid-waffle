package com.crocdc.datadatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.crocdc.datadatabase.model.EncountersEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EncountersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(encountersEntity: EncountersEntity)

    @Query("SELECT * FROM EncountersEntity WHERE name  == :name")
    fun getEncountersEntity(name: String): Flow<EncountersEntity?>
}
