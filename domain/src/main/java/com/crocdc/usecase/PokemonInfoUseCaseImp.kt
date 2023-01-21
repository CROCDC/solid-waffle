package com.crocdc.delegate

import com.crocdc.datacore.repos.PokemonRepository
import com.crocdc.domain.model.PokemonInfo
import com.crocdc.mapper.PokemonInfoMapper
import com.crocdc.usecase.PokemonInfoUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class PokemonInfoUseCaseImp @Inject constructor(
    private val pokemonRepository: PokemonRepository,
) : PokemonInfoUseCase {
    override operator fun invoke(name: Flow<String?>): Flow<PokemonInfo?> = name.flatMapLatest {
        it?.let {
            pokemonRepository.getPokemonInfo(it).map { entity ->
                entity?.let { PokemonInfoMapper.transform(it) }
            }
        } ?: emptyFlow()
    }
}