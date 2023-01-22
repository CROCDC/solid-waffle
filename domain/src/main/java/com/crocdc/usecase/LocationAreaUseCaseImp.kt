package com.crocdc.usecase

import com.crocdc.datacore.repos.LocationAreaRepository
import com.crocdc.datacore.repos.OfflineLocationAreaRepository
import com.crocdc.domain.model.LocationArea
import com.crocdc.mapper.LocationAreaMapper
import com.crocdc.util.NetworkStatusTracker
import com.crocdc.util.flatMapLatest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class LocationAreaUseCaseImp @Inject constructor(
    private val networkStatusTracker: NetworkStatusTracker,
    private val locationAreaRepository: LocationAreaRepository,
    private val offlineLocationAreaRepository: OfflineLocationAreaRepository
) : LocationAreaUseCase {

    override fun invoke(id: Flow<String?>): Flow<LocationArea?> =
        id.flatMapLatest { idLet ->
            idLet?.let {
                networkStatusTracker.networkStatus.flatMapLatest(
                    onAvailable = {
                        locationAreaRepository.getLocationArea(idLet).map {
                            it?.let { LocationAreaMapper.transform(it) }
                        }
                    },
                    onUnavailable = {
                        offlineLocationAreaRepository.getLocationArea(idLet).map {
                            it?.let { LocationAreaMapper.transform(it) }
                        }
                    }
                )
            } ?: emptyFlow()
        }
}
