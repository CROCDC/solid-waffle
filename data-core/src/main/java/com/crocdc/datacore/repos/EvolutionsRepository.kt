package com.crocdc.datacore.repos

import com.crocdc.datacore.networkBoundResource
import com.crocdc.datadatabase.dao.EvolutionDao
import com.crocdc.datadatabase.model.EvolutionEntity
import com.crocdc.datadatabase.model.EvolvesTo
import com.crocdc.datadatabase.model.EvolvesTo2
import com.crocdc.datadatabase.model.PokemonEvolution
import com.crocdc.datanetworking.datasource.EvolutionsDataSourceProvider
import javax.inject.Inject

class EvolutionsRepository @Inject constructor(
    private val dao: EvolutionDao,
    private val dataSource: EvolutionsDataSourceProvider
) {

    fun getEvolutions(evolutionChain: String) = networkBoundResource(
        query = { dao.getEvolutionEntity(evolutionChain) },
        fetch = { dataSource.getEvolutions(evolutionChain) },
        saveFetchResult = {
            it.data?.let {
                val evolvesTo = it.chain.evolvesTo[0]
                val evolvesTo2 = it.chain.evolvesTo[0].evolvesTo?.get(0)
                dao.save(
                    EvolutionEntity(
                        evolutionChain,
                        PokemonEvolution(it.chain.species.name, it.chain.species.urlToId()),
                        EvolvesTo(
                            evolvesTo.evolutionDetails[0].minLevel,
                            PokemonEvolution(
                                evolvesTo.species.name,
                                evolvesTo.species.urlToId()
                            ),
                            evolvesTo2?.let {
                                EvolvesTo2(
                                    it.evolutionDetails[0].minLevel,
                                    PokemonEvolution(
                                        it.species.name,
                                        it.species.urlToId()
                                    )
                                )
                            }
                        )
                    )
                )
            }
        },
        shouldFetch = { it == null }
    )
}