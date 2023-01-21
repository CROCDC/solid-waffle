package com.crocdc.usecase

import com.crocdc.domain.model.Area
import kotlinx.coroutines.flow.Flow

interface AreasUseCase {
    operator fun invoke(name: Flow<String?>): Flow<List<Area>>
}
