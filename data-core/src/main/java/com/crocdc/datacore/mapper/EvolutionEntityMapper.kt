package com.crocdc.datacore.mapper

import com.crocdc.datadatabase.model.EvolutionEntity
import com.crocdc.datadatabase.model.EvolvesTo
import com.crocdc.datadatabase.model.EvolvesTo2
import com.crocdc.datadatabase.model.PokemonEvolution
import com.crocdc.modelnetworking.EvolutionResponse

class EvolutionEntityMapper(private val chain: String) :
    BaseMapper<EvolutionResponse, EvolutionEntity?>() {
    override fun transform(inputModel: EvolutionResponse): EvolutionEntity? {
        val evolvesTo = inputModel.chain.evolvesTo.firstOrNull()
        val evolvesTo2 = evolvesTo?.evolvesTo?.firstOrNull()
        return evolvesTo?.let { evolvesTo1 ->
            EvolutionEntity(
                chain,
                PokemonEvolution(inputModel.chain.species.name, inputModel.chain.species.urlToId()),
                EvolvesTo(
                    evolvesTo1.evolutionDetails.firstOrNull()?.minLevel,
                    PokemonEvolution(
                        evolvesTo1.species.name,
                        evolvesTo1.species.urlToId()
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
        }
    }
}
