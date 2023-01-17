package com.crocdc.datadatabase

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.crocdc.datadatabase.dao.PokemonDao
import com.crocdc.datadatabase.model.PokemonEntity
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.io.IOException

@RunWith(RobolectricTestRunner::class)
class PokemonDaoTest {

    private lateinit var pokemonDao: PokemonDao
    private lateinit var db: PokemonDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().context
        db = Room.inMemoryDatabaseBuilder(
            context, PokemonDatabase::class.java
        ).allowMainThreadQueries().build()
        pokemonDao = db.pokemonDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    @Throws(Exception::class)
    fun saveAndSearch() = runTest {
        val productEntity = PokemonEntity("7", "Squirtle")
        pokemonDao.saveAll(listOf(productEntity))
        pokemonDao.search("S").take(1).collect {
            assertEquals(productEntity.id, it[0].id)
        }
    }
}
