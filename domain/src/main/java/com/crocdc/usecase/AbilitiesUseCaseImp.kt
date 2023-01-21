package com.crocdc.usecase

import com.crocdc.delegate.PokemonInfoDelegate
import com.crocdc.domain.model.Ability
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AbilitiesUseCaseImp @Inject constructor(
    pokemonInfoDelegate: PokemonInfoDelegate
) : AbilitiesUseCase, PokemonInfoDelegate by pokemonInfoDelegate {

    override val abilities: Flow<List<Ability>> = pokemonInfo.map {
        it?.abilities ?: emptyList()
    }
}