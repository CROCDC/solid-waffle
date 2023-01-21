package com.crocdc.solidwaffle.vm

import androidx.lifecycle.ViewModel
import com.crocdc.delegate.EvolutionsUseCase
import com.crocdc.domain.model.FromEvolutionTo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class EvolutionsViewModel @Inject constructor(
    useCase: EvolutionsUseCase
) : ViewModel() {

    val name: MutableStateFlow<String?> = MutableStateFlow(null)

    val evolutions: Flow<List<FromEvolutionTo>> = useCase.invoke(name)

    suspend fun setName(name: String?) {
        this.name.emit(name)
    }
}
