package com.crocdc.usecase

import com.crocdc.datacore.repos.EvolutionsRepository
import com.crocdc.datacore.repos.PokemonSpecieRepository
import com.crocdc.delegate.PokemonInfoDelegate
import com.crocdc.domain.model.FromEvolutionTo
import com.crocdc.domain.model.Pokemon
import com.crocdc.domain.model.PokemonSpecie
import com.crocdc.mapper.PokemonSpecieMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class EvolutionsUseCaseImp @Inject constructor(
    evolutionsRepository: EvolutionsRepository,
    specieRepository: PokemonSpecieRepository,
    pokemonInfoDelegate: PokemonInfoDelegate
) : EvolutionsUseCase, PokemonInfoDelegate by pokemonInfoDelegate {

    override val pokemonSpecie: Flow<PokemonSpecie?> = name.flatMapLatest { name ->
        name?.let {
            specieRepository.getPokemonSpecie(name).map {
                it?.let { PokemonSpecieMapper.transform(it) }
            }
        } ?: emptyFlow()
    }

    override val evolutions: Flow<List<FromEvolutionTo>> = pokemonSpecie.flatMapLatest { specie ->
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
}