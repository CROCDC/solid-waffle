package com.crocdc.datadatabase

import com.crocdc.datadatabase.dao.EncountersDao
import com.crocdc.datadatabase.model.Encounter
import com.crocdc.datadatabase.model.EncountersEntity
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class EncountersDaoTest : DaoTest() {

    private val dao: Lazy<EncountersDao> = lazy {
        db.encountersDao()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    @Throws(Exception::class)
    fun saveAndGet() = runTest {
        val encounters = EncountersEntity(name, listOf(Encounter("area", "id")))
        dao.value.save(encounters)
        dao.value.getEncountersEntity(name).take(1).collect {
            TestCase.assertEquals(name, it?.name)
        }
    }

    companion object {
        private const val name = "name"
    }
}
