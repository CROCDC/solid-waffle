package com.crocdc.usecase

import com.crocdc.domain.model.Ability
import kotlinx.coroutines.flow.Flow

interface AbilitiesUseCase {
    operator fun invoke(name: Flow<String?>): Flow<List<Ability>>
}
