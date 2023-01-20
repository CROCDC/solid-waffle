package com.crocdc.datadatabase

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.crocdc.datadatabase.dao.EncountersDao
import com.crocdc.datadatabase.model.EncountersEntity
import junit.framework.TestCase
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
class EncountersDaoTest {

    private lateinit var dao: EncountersDao
    private lateinit var db: PokemonDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().context
        db = Room.inMemoryDatabaseBuilder(
            context, PokemonDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = db.encountersDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    @Throws(Exception::class)
    fun saveAndGet() = runTest {
        val evolutionEntity = EncountersEntity(name, "area")
        dao.save(evolutionEntity)
        dao.getEvolutionEntity(name).take(1).collect {
            TestCase.assertEquals(name, it?.name)
        }
    }

    companion object {
        private const val name = "name"
    }
}