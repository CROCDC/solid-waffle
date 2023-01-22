package com.crocdc.datadatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.crocdc.datadatabase.model.LocationAreaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationAreaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(locationAreaEntity: LocationAreaEntity)

    @Query("SELECT * FROM LocationAreaEntity WHERE id == :id")
    fun getLocationAreaEntity(id: String): Flow<LocationAreaEntity?>
}
