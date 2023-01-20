package com.crocdc.usecase

import com.crocdc.datacore.model.PokemonInfo
import com.crocdc.datacore.repos.PokemonRepository
import com.crocdc.mapper.PokemonInfoMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokemonInfoUseCaseImp @Inject constructor(
    repository: PokemonRepository
) : PokemonInfoUseCase {

    private val name: MutableStateFlow<String?> = MutableStateFlow(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    override val pokemonInfo: Flow<PokemonInfo?> = name.flatMapLatest { name ->
        name?.let {
            repository.getPokemonInfo(name).map { entity ->
                entity?.let { PokemonInfoMapper.transform(it) }
            }
        } ?: emptyFlow()
    }

    override suspend fun setName(name: String) {
        this.name.emit(name)
    }
}