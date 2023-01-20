package com.crocdc.datadatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.crocdc.datadatabase.model.PokemonSpecieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonSpecieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(pokemonSpecie: PokemonSpecieEntity)

    @Query("SELECT * FROM PokemonSpecieEntity WHERE name == :name")
    fun getPokemonSpecie(name: String): Flow<PokemonSpecieEntity?>
}