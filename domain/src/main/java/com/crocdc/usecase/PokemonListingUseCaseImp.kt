package com.crocdc.usecase

import com.crocdc.datacore.model.Pokemon
import com.crocdc.datacore.repos.PokemonRepository
import com.crocdc.mapper.PokemonMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokemonListingUseCaseImp @Inject constructor(
    repository: PokemonRepository
) : PokemonListingUseCase {

    private val query: MutableStateFlow<String?> = MutableStateFlow(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    override val pokemons: Flow<List<Pokemon>> = query.flatMapLatest { query ->
        repository.getPokemonsListing(query).map {
            PokemonMapper.transform(it)
        }

    }

    override suspend fun setQuery(query: String?) {
        this.query.emit(query)
    }
}