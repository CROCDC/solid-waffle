package com.crocdc.usecase

import com.crocdc.delegate.PokemonInfoDelegate
import com.crocdc.domain.model.Ability
import kotlinx.coroutines.flow.Flow

interface AbilitiesUseCase : PokemonInfoDelegate {
    val abilities: Flow<List<Ability>>
}
