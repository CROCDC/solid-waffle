package com.crocdc.datadatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.crocdc.datadatabase.model.PokemonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Query("SELECT * FROM PokemonEntity WHERE name LIKE :query || '%'")
    fun search(query: String?): Flow<List<PokemonEntity>>

    @Query("SELECT * FROM PokemonEntity")
    fun getAll(): Flow<List<PokemonEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(products: List<PokemonEntity>)
}
