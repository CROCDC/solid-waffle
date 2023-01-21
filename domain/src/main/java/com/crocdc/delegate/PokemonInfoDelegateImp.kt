package com.crocdc.delegate

import com.crocdc.datacore.repos.PokemonRepository
import com.crocdc.domain.model.PokemonInfo
import com.crocdc.mapper.PokemonInfoMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class PokemonInfoDelegateImp @Inject constructor(
    pokemonRepository: PokemonRepository,
) : PokemonInfoDelegate {

    override val name: MutableStateFlow<String?> = MutableStateFlow(null)

    override val pokemonInfo: Flow<PokemonInfo?> = name.flatMapLatest { name ->
        name?.let {
            pokemonRepository.getPokemonInfo(name).map { entity ->
                entity?.let { PokemonInfoMapper.transform(it) }
            }
        } ?: emptyFlow()
    }

    override suspend fun setName(name: String) {
        this.name.emit(name)
    }
}