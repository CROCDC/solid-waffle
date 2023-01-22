package com.crocdc.datadatabase

import com.crocdc.datadatabase.dao.PokemonDao
import com.crocdc.datadatabase.model.PokemonEntity
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class PokemonDaoTest : DaoTest() {

    private val dao: Lazy<PokemonDao> = lazy {
        db.pokemonDao()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    @Throws(Exception::class)
    fun saveAndSearch() = runTest {
        val productEntity = PokemonEntity("7", "Squirtle")
        dao.value.saveAll(listOf(productEntity))
        dao.value.search("S").take(1).collect {
            assertEquals(productEntity.id, it[0].id)
        }
    }
}
