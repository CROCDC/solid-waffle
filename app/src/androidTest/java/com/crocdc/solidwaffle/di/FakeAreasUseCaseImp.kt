package com.crocdc.solidwaffle.di

import com.crocdc.domain.model.Area
import com.crocdc.usecase.AreasUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeAreasUseCaseImp : AreasUseCase {
    override fun invoke(name: Flow<String?>): Flow<List<Area>> = flow {

    }
}
