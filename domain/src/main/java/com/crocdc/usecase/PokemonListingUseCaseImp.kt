package com.crocdc.delegate

import com.crocdc.datacore.repos.PokemonRepository
import com.crocdc.delegate.PokemonListingUseCase
import com.crocdc.domain.model.Pokemon
import com.crocdc.mapper.PokemonMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokemonListingUseCaseImp @Inject constructor(
    private val repository: PokemonRepository
) : PokemonListingUseCase {

    override fun invoke(query: Flow<String?>): Flow<List<Pokemon>> = query.flatMapLatest { query ->
        repository.getPokemonsListing(query).map {
            PokemonMapper.transform(it)
        }
    }
}
