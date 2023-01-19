package com.crocdc.datadatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.crocdc.datadatabase.dao.EvolutionDao
import com.crocdc.datadatabase.dao.PokemonDao
import com.crocdc.datadatabase.dao.PokemonInfoDao
import com.crocdc.datadatabase.model.EvolutionEntity
import com.crocdc.datadatabase.model.PokemonEntity
import com.crocdc.datadatabase.model.PokemonInfoEntity

@Database(
    entities = [PokemonEntity::class, PokemonInfoEntity::class, EvolutionEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao

    abstract fun pokemonInfoDao(): PokemonInfoDao

    abstract fun evolutionDao(): EvolutionDao

    companion object {
        const val DATABASE_NAME: String = "pokemon_database"
    }
}
