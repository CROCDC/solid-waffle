package com.crocdc.usecase

import com.crocdc.domain.model.FromEvolutionTo
import kotlinx.coroutines.flow.Flow

interface EvolutionsUseCase {
    operator fun invoke(name: Flow<String?>): Flow<List<FromEvolutionTo>>
}

