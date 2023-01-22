package com.crocdc.usecase

import com.crocdc.datacore.repos.OfflinePokemonRepository
import com.crocdc.datacore.repos.PokemonRepository
import com.crocdc.domain.model.Pokemon
import com.crocdc.mapper.PokemonMapper
import com.crocdc.util.NetworkStatusTracker
import com.crocdc.util.flatMapLatest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class PokemonListingUseCaseImp @Inject constructor(
    private val repository: PokemonRepository,
    private val offlinePokemonRepository: OfflinePokemonRepository,
    private val networkStatusTracker: NetworkStatusTracker
) : PokemonListingUseCase {

    override fun invoke(query: Flow<String?>): Flow<List<Pokemon>> =
        query.flatMapLatest {
            networkStatusTracker.networkStatus.flatMapLatest(
                onAvailable = {
                    repository.getPokemonsListing(it).map {
                        PokemonMapper.transform(it)
                    }
                },
                onUnavailable = {
                    offlinePokemonRepository.getPokemonsListing(it).map {
                        PokemonMapper.transform(it)
                    }
                }
            )
        }
}
