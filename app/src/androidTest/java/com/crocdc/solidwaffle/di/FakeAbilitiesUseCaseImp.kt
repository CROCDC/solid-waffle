package com.crocdc.solidwaffle.di

import com.crocdc.domain.model.Ability
import com.crocdc.solidwaffle.util.MockFactory
import com.crocdc.usecase.AbilitiesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeAbilitiesUseCaseImp : AbilitiesUseCase {
    override fun invoke(name: Flow<String?>): Flow<List<Ability>> = MutableStateFlow(
        listOf(MockFactory.ability)
    )
}
