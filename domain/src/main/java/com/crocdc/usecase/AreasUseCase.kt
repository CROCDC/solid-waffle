package com.crocdc.usecase

import com.crocdc.delegate.PokemonInfoDelegate
import com.crocdc.domain.model.Area
import kotlinx.coroutines.flow.Flow

interface AreasUseCase : PokemonInfoDelegate {
    val areas: Flow<List<Area>>
}
