package com.crocdc.datacore.repos

import com.crocdc.datacore.offlineBoundResource
import com.crocdc.datadatabase.dao.PokemonSpecieDao
import com.crocdc.datadatabase.model.PokemonSpecieEntity
import com.crocdc.dataoffline.datasource.OfflinePokemonSpeciesDataSourceProvider
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfflinePokemonSpecieRepository @Inject constructor(
    private val dao: PokemonSpecieDao,
    private val dataSource: OfflinePokemonSpeciesDataSourceProvider
) {
    fun getPokemonSpecie(name: String): Flow<PokemonSpecieEntity?> = offlineBoundResource(
        query = {
            dao.getPokemonSpecie(name)
        },
        fetch = {
            dataSource.getPokemonSpecies(name)
        },
        saveFetchResult = {
            it?.let {
                dao.save(PokemonSpecieEntity(name, it.evolutionChain.urlToId()))
            }
        }
    )
}
