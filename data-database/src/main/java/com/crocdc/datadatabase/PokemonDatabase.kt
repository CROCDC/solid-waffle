package com.crocdc.datadatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.crocdc.datadatabase.dao.PokemonDao
import com.crocdc.datadatabase.model.PokemonEntity

@Database(
    entities = [PokemonEntity::class],
    version = 1
)

abstract class PokemonDatabase : RoomDatabase() {
    abstract fun productDao(): PokemonDao

    companion object {
        const val DATABASE_NAME: String = "pokemon_database"
    }
}
