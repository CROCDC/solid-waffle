package com.crocdc.solidwaffle.di

import com.crocdc.domain.model.FromEvolutionTo
import com.crocdc.usecase.EvolutionsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeEvolutionsUseCaseImp : EvolutionsUseCase{
    override fun invoke(name: Flow<String?>): Flow<List<FromEvolutionTo>> = flow {

    }
}
