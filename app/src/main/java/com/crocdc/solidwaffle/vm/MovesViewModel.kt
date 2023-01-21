package com.crocdc.solidwaffle.vm

import androidx.lifecycle.ViewModel
import com.crocdc.delegate.MovesUseCase
import com.crocdc.domain.model.PokemonMove
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MovesViewModel @Inject constructor(
    useCase: MovesUseCase
) : ViewModel() {

    private val name: MutableStateFlow<String?> = MutableStateFlow(null)

    val moves: Flow<List<PokemonMove>> = useCase.invoke(name)

    suspend fun setName(name: String?) {
        this.name.emit(name)
    }
}