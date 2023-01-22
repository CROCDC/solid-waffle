package com.crocdc.usecase

import com.crocdc.datacore.repos.OfflinePokemonRepository
import com.crocdc.datacore.repos.PokemonRepository
import com.crocdc.domain.model.PokemonInfo
import com.crocdc.mapper.PokemonInfoMapper
import com.crocdc.util.NetworkStatusTracker
import com.crocdc.util.flatMapLatest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class PokemonInfoUseCaseImp @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    private val offlinePokemonRepository: OfflinePokemonRepository,
    private val networkStatusTracker: NetworkStatusTracker
) : PokemonInfoUseCase {
    override operator fun invoke(name: Flow<String?>): Flow<PokemonInfo?> = name.flatMapLatest { it ->
        it?.let { name ->
            networkStatusTracker.networkStatus.flatMapLatest(
                onUnavailable = {
                    offlinePokemonRepository.getPokemonInfo(name).map { entity ->
                        entity?.let { PokemonInfoMapper.transform(it) }
                    }
                },
                onAvailable = {
                    pokemonRepository.getPokemonInfo(name).map { entity ->
                        entity?.let { PokemonInfoMapper.transform(it) }
                    }
                }
            )
        } ?: emptyFlow()
    }
}
