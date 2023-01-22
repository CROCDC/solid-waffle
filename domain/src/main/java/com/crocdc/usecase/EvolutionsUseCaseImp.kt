package com.crocdc.usecase

import com.crocdc.datacore.repos.EvolutionsRepository
import com.crocdc.datacore.repos.OfflineEvolutionsRepository
import com.crocdc.datacore.repos.OfflinePokemonSpecieRepository
import com.crocdc.datacore.repos.PokemonSpecieRepository
import com.crocdc.domain.model.FromEvolutionTo
import com.crocdc.mapper.FromEvolutionToMapper
import com.crocdc.util.NetworkStatusTracker
import com.crocdc.util.flatMapLatest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class EvolutionsUseCaseImp @Inject constructor(
    private val evolutionsRepository: EvolutionsRepository,
    private val offlineEvolutionsRepository: OfflineEvolutionsRepository,
    private val pokemonSpecieRepository: PokemonSpecieRepository,
    private val offlinePokemonSpecieRepository: OfflinePokemonSpecieRepository,
    private val networkStatusTracker: NetworkStatusTracker
) : EvolutionsUseCase {
    override fun invoke(name: Flow<String?>): Flow<List<FromEvolutionTo>> =
        name.flatMapLatest { it ->
            it?.let { name ->
                networkStatusTracker.networkStatus.flatMapLatest(
                    onAvailable = {
                        pokemonSpecieRepository.getPokemonSpecie(name).flatMapLatest { specie ->
                            specie?.let {
                                evolutionsRepository.getEvolutions(specie.evolutionChain)
                                    .mapLatest {
                                        it?.let { entity ->
                                            FromEvolutionToMapper.transform(entity)
                                        } ?: emptyList()
                                    }
                            } ?: emptyFlow()
                        }
                    },
                    onUnavailable = {
                        offlinePokemonSpecieRepository.getPokemonSpecie(name)
                            .flatMapLatest { specie ->
                                specie?.let {
                                    offlineEvolutionsRepository.getEvolutions(specie.evolutionChain)
                                        .mapLatest {
                                            it?.let { entity ->
                                                FromEvolutionToMapper.transform(entity)
                                            } ?: emptyList()
                                        }
                                } ?: emptyFlow()
                            }
                    }
                )
            } ?: emptyFlow()
        }
}
