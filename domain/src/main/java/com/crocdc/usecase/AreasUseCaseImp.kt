package com.crocdc.usecase

import com.crocdc.datacore.repos.EncountersRepository
import com.crocdc.datacore.repos.OfflineEncountersRepository
import com.crocdc.domain.model.Area
import com.crocdc.mapper.AreaMapper
import com.crocdc.util.NetworkStatusTracker
import com.crocdc.util.flatMapLatest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class AreasUseCaseImp @Inject constructor(
    private val encountersRepository: EncountersRepository,
    private val offlineEncountersRepository: OfflineEncountersRepository,
    private val networkStatusTracker: NetworkStatusTracker
) : AreasUseCase {

    override fun invoke(name: Flow<String?>): Flow<List<Area>> = name.flatMapLatest {
        it?.let {
            networkStatusTracker.networkStatus.flatMapLatest(
                onAvailable = {
                    encountersRepository.getEncounters(it).map {
                        it?.let { AreaMapper.transform(it) } ?: emptyList()
                    }
                },
                onUnavailable = {
                    offlineEncountersRepository.getEncounters(it).map {
                        it?.let { AreaMapper.transform(it) } ?: emptyList()
                    }
                }
            )
        } ?: emptyFlow()
    }
}
