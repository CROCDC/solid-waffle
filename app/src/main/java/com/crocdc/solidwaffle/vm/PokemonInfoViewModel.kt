package com.crocdc.solidwaffle.vm

import androidx.lifecycle.ViewModel
import com.crocdc.datacore.model.PokemonInfo
import com.crocdc.datacore.repos.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class PokemonInfoViewModel @Inject constructor(repository: PokemonRepository) : ViewModel() {

    private val name: MutableStateFlow<String?> = MutableStateFlow(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    val pokemonInfo: Flow<PokemonInfo?> = name.flatMapLatest {
        it?.let {
            repository.getPokemonInfo(it)
        } ?: emptyFlow()
    }

    suspend fun setName(name: String) {
        this.name.emit(name)
    }
}