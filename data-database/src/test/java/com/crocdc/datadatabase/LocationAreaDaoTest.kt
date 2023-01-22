package com.crocdc.datadatabase

import com.crocdc.datadatabase.dao.LocationAreaDao
import com.crocdc.datadatabase.model.LocationAreaEntity
import com.crocdc.datadatabase.model.PokemonArea
import com.crocdc.datadatabase.model.PokemonEncounter
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class LocationAreaDaoTest : DaoTest() {

    val dao: Lazy<LocationAreaDao> = lazy {
        db.locationAreaDao()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    @Throws(Exception::class)
    fun saveAndGet() = runTest {
        val evolutionEntity = LocationAreaEntity(
            id,
            "name",
            listOf(
                PokemonEncounter(
                    PokemonArea(
                        "Squirtle",
                        "image"
                    )
                )
            )
        )
        dao.value.save(evolutionEntity)
        dao.value.getLocationAreaEntity(id).take(1).collect {
            TestCase.assertEquals(evolutionEntity.id, it?.id)
            TestCase.assertEquals(evolutionEntity.pokemonEncounters, it?.pokemonEncounters)
        }
    }

    companion object {
        private const val id = "id"
    }
}