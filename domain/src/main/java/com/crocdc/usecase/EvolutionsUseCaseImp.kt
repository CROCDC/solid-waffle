package com.crocdc.usecase

import com.crocdc.datacore.repos.EvolutionsRepository
import com.crocdc.datacore.repos.PokemonSpecieRepository
import com.crocdc.domain.model.FromEvolutionTo
import com.crocdc.domain.model.Pokemon
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class EvolutionsUseCaseImp @Inject constructor(
    private val evolutionsRepository: EvolutionsRepository,
    private val pokemonSpecieRepository: PokemonSpecieRepository
) : EvolutionsUseCase {
    override fun invoke(name: Flow<String?>): Flow<List<FromEvolutionTo>> = name.flatMapLatest {
        it?.let {
            pokemonSpecieRepository.getPokemonSpecie(it).flatMapLatest { specie ->
                specie?.let {
                    evolutionsRepository.getEvolutions(specie.evolutionChain).mapLatest {
                        it?.let { entity ->
                            val second = it.evolvesTo.evolvesTo?.let {
                                FromEvolutionTo(
                                    Pokemon(
                                        entity.evolvesTo.pokemonEvolution.name,
                                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${entity.evolvesTo.pokemonEvolution.id}.png"
                                    ),
                                    it.minLevel,
                                    Pokemon(
                                        it.pokemonEvolution.name,
                                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${it.pokemonEvolution.id}.png"

                                    )
                                )
                            }
                            listOf(
                                FromEvolutionTo(
                                    Pokemon(
                                        entity.basePokemon.name,
                                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${entity.basePokemon.id}.png"
                                    ),
                                    entity.evolvesTo.minLevel,
                                    Pokemon(
                                        entity.evolvesTo.pokemonEvolution.name,
                                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${entity.evolvesTo.pokemonEvolution.id}.png"
                                    )
                                )
                            ).plus(second).filterNotNull()
                        } ?: emptyList()
                    }
                } ?: emptyFlow()
            }
        } ?: emptyFlow()
    }
}