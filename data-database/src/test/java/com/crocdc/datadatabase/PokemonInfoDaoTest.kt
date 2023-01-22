package com.crocdc.datadatabase

import com.crocdc.datadatabase.dao.PokemonInfoDao
import com.crocdc.datadatabase.model.Ability
import com.crocdc.datadatabase.model.LearnedAt
import com.crocdc.datadatabase.model.Move
import com.crocdc.datadatabase.model.PokemonEntity
import com.crocdc.datadatabase.model.PokemonInfoEntity
import com.crocdc.datadatabase.model.Type
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class PokemonInfoDaoTest : DaoTest() {

    private val dao: Lazy<PokemonInfoDao> = lazy {
        db.pokemonInfoDao()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    @Throws(Exception::class)
    fun saveAndGet() = runTest {
        val productEntity = PokemonEntity(name, "")
        dao.value.save(
            PokemonInfoEntity(
                name,
                listOf(Type("water")),
                listOf(
                    Move(
                        "tackle",
                        listOf(
                            LearnedAt(
                                20,
                                "LEVEL"
                            )
                        )
                    )
                ),
                listOf(Ability("blaze", true)),
                "river",
                "image",
                "shiny",
            )
        )
        dao.value.getPokemonInfoEntity(name).take(1).collect {
            TestCase.assertEquals(productEntity.id, name)
        }
    }

    companion object {
        private const val name = "Squirtle"
    }
}
