package com.crocdc.datadatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.crocdc.datadatabase.model.PokemonInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(pokemonInfoEntity: PokemonInfoEntity)

    @Query("SELECT * FROM PokemonInfoEntity WHERE id == :id")
    fun getPokemonInfoEntity(id: String): Flow<PokemonInfoEntity>
}