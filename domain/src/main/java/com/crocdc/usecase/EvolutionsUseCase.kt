package com.crocdc.usecase

import com.crocdc.delegate.PokemonInfoDelegate
import com.crocdc.domain.model.FromEvolutionTo
import com.crocdc.domain.model.PokemonSpecie
import kotlinx.coroutines.flow.Flow

interface EvolutionsUseCase : PokemonInfoDelegate {
    val pokemonSpecie: Flow<PokemonSpecie?>
    val evolutions: Flow<List<FromEvolutionTo>>
}

