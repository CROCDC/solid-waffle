package com.crocdc.usecase

import com.crocdc.domain.model.Ability
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AbilitiesUseCaseImp @Inject constructor(
    private val pokemonInfoUseCase: PokemonInfoUseCase
) : AbilitiesUseCase {
    override fun invoke(name: Flow<String?>): Flow<List<Ability>> =
        pokemonInfoUseCase.invoke(name).map {
            it?.abilities ?: emptyList()
        }
}