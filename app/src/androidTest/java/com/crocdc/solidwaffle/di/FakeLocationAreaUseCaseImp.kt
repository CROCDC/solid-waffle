package com.crocdc.solidwaffle.di

import com.crocdc.domain.model.LocationArea
import com.crocdc.usecase.LocationAreaUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeLocationAreaUseCaseImp : LocationAreaUseCase {
    override fun invoke(id: Flow<String?>): Flow<LocationArea?> = flow {

    }
}
