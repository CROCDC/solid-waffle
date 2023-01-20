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
                val evolvesTo2 = it.chain.evolvesTo[1]
                dao.save(
                    EvolutionEntity(
                        evolutionChain,
                        EvolvesTo(
                            evolvesTo.evolutionDetails[0].minLevel,
                            PokemonEvolution(
                                evolvesTo.species.name,
                                evolvesTo.species.urlToId()
                            ),
                            EvolvesTo2(
                                evolvesTo2.evolutionDetails[0].minLevel,
                                PokemonEvolution(
                                    evolvesTo2.species.name,
                                    evolvesTo.species.urlToId()
                                )
                            )
                        )
                    )
                )
            }
        },
        shouldFetch = { it != null }
    )
}