package com.crocdc.datadatabase

import androidx.room.TypeConverter
import com.crocdc.datadatabase.model.Ability
import com.crocdc.datadatabase.model.Encounter
import com.crocdc.datadatabase.model.Move
import com.crocdc.datadatabase.model.Type
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class Converters {

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @TypeConverter
    fun saveTypes(types: List<Type>): String {

        val type = Types.newParameterizedType(List::class.java, Type::class.java)
        return moshi.adapter<List<Type>>(type).toJson(types)
    }

    @TypeConverter
    fun restoreTypes(types: String): List<Type>? {
        val type = Types.newParameterizedType(List::class.java, Type::class.java)
        return moshi.adapter<List<Type>>(type).fromJson(types)
    }

    @TypeConverter
    fun saveMoves(moves: List<Move>): String {
        val type = Types.newParameterizedType(List::class.java, Move::class.java)
        return moshi.adapter<List<Move>>(type).toJson(moves)
    }

    @TypeConverter
    fun restoreMoves(moves: String): List<Move>? {
        val type = Types.newParameterizedType(List::class.java, Move::class.java)
        return moshi.adapter<List<Move>>(type).fromJson(moves)
    }

    @TypeConverter
    fun saveAbilities(abilities: List<Ability>): String {
        val type = Types.newParameterizedType(List::class.java, Ability::class.java)
        return moshi.adapter<List<Ability>>(type).toJson(abilities)
    }

    @TypeConverter
    fun restoreAbilities(abilities: String): List<Ability>? {
        val type = Types.newParameterizedType(List::class.java, Ability::class.java)
        return moshi.adapter<List<Ability>>(type).fromJson(abilities)
    }

    @TypeConverter
    fun saveEncounters(encounters: List<Encounter>): String {
        val type = Types.newParameterizedType(List::class.java, Encounter::class.java)
        return moshi.adapter<List<Encounter>>(type).toJson(encounters)
    }

    @TypeConverter
    fun restoreEncounters(encounters: String): List<Encounter>? {
        val type = Types.newParameterizedType(List::class.java, Encounter::class.java)
        return moshi.adapter<List<Encounter>>(type).fromJson(encounters)
    }
}