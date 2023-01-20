package com.crocdc.datadatabase

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.crocdc.datadatabase.dao.PokemonInfoDao
import com.crocdc.datadatabase.model.Ability
import com.crocdc.datadatabase.model.Move
import com.crocdc.datadatabase.model.PokemonEntity
import com.crocdc.datadatabase.model.PokemonInfoEntity
import com.crocdc.datadatabase.model.Type
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
class PokemonInfoDaoTest {

    private lateinit var dao: PokemonInfoDao
    private lateinit var db: PokemonDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().context
        db = Room.inMemoryDatabaseBuilder(
            context, PokemonDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = db.pokemonInfoDao()
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
        val productEntity = PokemonEntity(name, "")
        dao.save(
            PokemonInfoEntity(
                name,
                listOf(Type("water")),
                listOf(Move("tackle")),
                listOf(Ability("blaze")),
                "river",
                "image",
                "sprite"
            )
        )
        dao.getPokemonInfoEntity(name).take(1).collect {
            TestCase.assertEquals(productEntity.id, name)
        }
    }

    companion object {
        private const val name = "Squirtle"
    }
}