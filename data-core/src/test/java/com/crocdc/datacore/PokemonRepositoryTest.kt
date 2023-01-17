package com.crocdc.datacore

import com.crocdc.datacore.repos.PokemonRepository
import com.crocdc.datadatabase.dao.PokemonDao
import com.crocdc.datadatabase.model.PokemonEntity
import com.crocdc.datanetworking.datasource.PokemonDataSourceProvider
import io.mockk.Called
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runTest
import org.junit.Test

// TODO add more tests
class PokemonRepositoryTest {

    private val dataSource: PokemonDataSourceProvider = mockk()

    private val productDao: PokemonDao = mockk()

    private val repository: PokemonRepository = PokemonRepository(
        productDao,
        dataSource
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun searchShouldFetchTrue() {
        every { productDao.getAll() } returns flow { emit(emptyList()) }
        runTest {
            repository.getPokemonsListing().take(4).collect {}
            verify { dataSource.getPokemonsListing() }
            verify { productDao.getAll() }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun searchShouldFetchFalse() {
        every { productDao.getAll() } returns flow { emit(listOf(pokemon)) }

        runTest {
            repository.getPokemonsListing().take(4).collect {}
            verify { dataSource.getPokemonsListing() wasNot Called }
            verify { productDao.getAll() }
        }
    }

    companion object {
        private val pokemon = PokemonEntity(
            "7",
            "Squirtle",
        )
    }
}
