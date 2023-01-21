package com.crocdc.usecase

import com.crocdc.datacore.repos.EncountersRepository
import com.crocdc.delegate.PokemonInfoDelegate
import com.crocdc.domain.model.Area
import com.crocdc.mapper.AreaMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class AreasUseCaseImp @Inject constructor(
    pokemonInfoDelegate: PokemonInfoDelegate,
    encountersRepository: EncountersRepository
) : AreasUseCase, PokemonInfoDelegate by pokemonInfoDelegate {

    override val areas: Flow<List<Area>> = name.flatMapLatest {
        it?.let {
            encountersRepository.getEncounters(it).map {
                it?.let { AreaMapper.transform(it) } ?: emptyList()
            }
        } ?: emptyFlow()
    }
}