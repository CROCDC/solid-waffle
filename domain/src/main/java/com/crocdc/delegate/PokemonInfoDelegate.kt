package com.crocdc.delegate

import com.crocdc.domain.model.PokemonInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

interface PokemonInfoDelegate {
    val name: MutableStateFlow<String?>
    val pokemonInfo: Flow<PokemonInfo?>
    suspend fun setName(name: String)
}