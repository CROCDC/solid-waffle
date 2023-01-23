package com.crocdc.mapper

import com.crocdc.datadatabase.model.EvolutionEntity
import com.crocdc.domain.model.FromEvolutionTo
import com.crocdc.domain.model.Pokemon

object FromEvolutionToMapper : BaseMapper<EvolutionEntity, List<FromEvolutionTo>>() {

    override fun transform(inputModel: EvolutionEntity): List<FromEvolutionTo> {
        val second = inputModel.evolvesTo.evolvesTo?.let {
            FromEvolutionTo(
                Pokemon(
                    inputModel.evolvesTo.pokemonEvolution.name,
                    "https://raw.githubusercontent.com/PokeAPI/sprites" +
                        "/master/sprites/pokemon/${inputModel.evolvesTo.pokemonEvolution.id}.png"
                ),
                it.minLevel,
                Pokemon(
                    it.pokemonEvolution.name,
                    "https://raw.githubusercontent.com/PokeAPI/sprites" +
                        "/master/sprites/pokemon/${it.pokemonEvolution.id}.png"
                )
            )
        }
        return listOf(
            FromEvolutionTo(
                Pokemon(
                    inputModel.basePokemon.name,
                    "https://raw.githubusercontent.com/PokeAPI/sprites" +
                        "/master/sprites/pokemon/${inputModel.basePokemon.id}.png"
                ),
                inputModel.evolvesTo.minLevel,
                Pokemon(
                    inputModel.evolvesTo.pokemonEvolution.name,
                    "https://raw.githubusercontent.com/PokeAPI/sprites" +
                        "/master/sprites/pokemon/${inputModel.evolvesTo.pokemonEvolution.id}.png"
                )
            )
        ).plus(second).filterNotNull()
    }
}
