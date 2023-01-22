package com.crocdc.datadatabase

import com.crocdc.datadatabase.dao.EvolutionDao
import com.crocdc.datadatabase.model.EvolutionEntity
import com.crocdc.datadatabase.model.EvolvesTo
import com.crocdc.datadatabase.model.EvolvesTo2
import com.crocdc.datadatabase.model.PokemonEvolution
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class EvolutionDaoTest : DaoTest() {

    private val dao: Lazy<EvolutionDao> = lazy {
        db.evolutionDao()
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
        dao.value.save(evolutionEntity)
        dao.value.getEvolutionEntity(chain).take(1).collect {
            TestCase.assertEquals(chain, it?.chain)
        }
    }

    companion object {
        private const val chain = "1"
    }
}
