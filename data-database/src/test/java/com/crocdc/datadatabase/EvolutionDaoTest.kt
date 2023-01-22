package com.crocdc.datadatabase

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.crocdc.datadatabase.dao.EvolutionDao
import com.crocdc.datadatabase.model.EvolutionEntity
import com.crocdc.datadatabase.model.EvolvesTo
import com.crocdc.datadatabase.model.EvolvesTo2
import com.crocdc.datadatabase.model.PokemonEvolution
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
class EvolutionDaoTest {

    private lateinit var dao: EvolutionDao
    private lateinit var db: PokemonDatabase

    // TODO stop repeating code
    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().context
        db = Room.inMemoryDatabaseBuilder(
            context, PokemonDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = db.evolutionDao()
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
        val evolutionEntity = EvolutionEntity(
            chain,
            PokemonEvolution("name", "id"),
            EvolvesTo(
                16,
                PokemonEvolution("wartortle", "image"),
                EvolvesTo2(
                    36,
                    PokemonEvolution(
                        "blastoise",
                        "image"
                    )
                )
            )

        )
        dao.save(evolutionEntity)
        dao.getEvolutionEntity(chain).take(1).collect {
            TestCase.assertEquals(chain, it?.chain)
        }
    }

    companion object {
        private const val chain = "1"
    }
}
