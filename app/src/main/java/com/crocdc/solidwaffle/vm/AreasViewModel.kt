package com.crocdc.solidwaffle.vm

import androidx.lifecycle.ViewModel
import com.crocdc.delegate.AreasUseCase
import com.crocdc.domain.model.Area
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class AreasViewModel @Inject constructor(
    useCase: AreasUseCase
) : ViewModel() {
    val name: MutableStateFlow<String?> = MutableStateFlow(null)

    val areas: Flow<List<Area>> = useCase.invoke(name)

    suspend fun setName(name: String?) {
        this.name.emit(name)
    }
}