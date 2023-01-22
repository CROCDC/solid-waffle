package com.crocdc.usecase

import com.crocdc.datacore.repos.PokemonRepository
import com.crocdc.domain.model.PokemonMove
import com.crocdc.mapper.PokemonMoveMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovesUseCaseImp @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : MovesUseCase {
    override fun invoke(name: Flow<String?>): Flow<List<PokemonMove>> = name.flatMapLatest {
        it?.let {
            pokemonRepository.getPokemonInfo(it).map {
                it?.moves?.let { PokemonMoveMapper.transform(it) } ?: emptyList()
            }
        } ?: emptyFlow()
    }
}
