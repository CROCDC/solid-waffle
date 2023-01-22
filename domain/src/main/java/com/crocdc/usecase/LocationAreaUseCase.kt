package com.crocdc.usecase

import com.crocdc.domain.model.LocationArea
import kotlinx.coroutines.flow.Flow

interface LocationAreaUseCase {
    fun invoke(id: Flow<String?>): Flow<LocationArea?>
}
