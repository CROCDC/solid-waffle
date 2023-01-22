package com.crocdc.usecase

import com.crocdc.domain.model.PokemonMove
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovesUseCaseImp @Inject constructor(
    private val pokemonInfoUseCase: PokemonInfoUseCase
) : MovesUseCase {
    override fun invoke(name: Flow<String?>): Flow<List<PokemonMove>> =
        pokemonInfoUseCase.invoke(name).map { entity ->
            entity?.pokemonMoves ?: emptyList()
        }
}
