package com.crocdc.usecase

import com.crocdc.datacore.repos.EvolutionsRepository
import com.crocdc.datacore.repos.PokemonRepository
import com.crocdc.datacore.repos.PokemonSpecieRepository
import com.crocdc.domain.model.FromEvolutionTo
import com.crocdc.domain.model.Pokemon
import com.crocdc.domain.model.PokemonInfo
import com.crocdc.domain.model.PokemonSpecie
import com.crocdc.mapper.PokemonInfoMapper
import com.crocdc.mapper.PokemonSpecieMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class PokemonInfoUseCaseImp @Inject constructor(
    pokemonRepository: PokemonRepository,
    specieRepository: PokemonSpecieRepository,
    evolutionsRepository: EvolutionsRepository
) : PokemonInfoUseCase {

    private val name: MutableStateFlow<String?> = MutableStateFlow(null)

    override val pokemonInfo: Flow<PokemonInfo?> = name.flatMapLatest { name ->
        name?.let {
            pokemonRepository.getPokemonInfo(name).map { entity ->
                entity?.let { PokemonInfoMapper.transform(it) }
            }
        } ?: emptyFlow()
    }
    override val pokemonSpecie: Flow<PokemonSpecie?> = name.flatMapLatest { name ->
        name?.let {
            specieRepository.getPokemonSpecie(name).map {
                it?.let { PokemonSpecieMapper.transform(it) }
            }
        } ?: emptyFlow()
    }

    override val evolutions: Flow<List<FromEvolutionTo>> = pokemonSpecie.flatMapLatest { specie ->
        specie?.let {
            evolutionsRepository.getEvolutions(specie.evolutionChain).map {
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

    override suspend fun setName(name: String) {
        this.name.emit(name)
    }
}