package com.crocdc.solidwaffle.vm

import androidx.lifecycle.ViewModel
import com.crocdc.delegate.PokemonListingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class PokemonListingViewModel @Inject constructor(
    useCase: PokemonListingUseCase
) : ViewModel() {
    private val query: MutableStateFlow<String?> = MutableStateFlow(null)

    val pokemons = useCase.invoke(query)

    suspend fun setQuery(query: String?) {
        this.query.emit(query)
    }
}
