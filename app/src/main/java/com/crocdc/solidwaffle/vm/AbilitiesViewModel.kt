package com.crocdc.solidwaffle.vm

import androidx.lifecycle.ViewModel
import com.crocdc.usecase.AbilitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class AbilitiesViewModel @Inject constructor(
    useCase: AbilitiesUseCase
) : ViewModel() {

    private val name: MutableStateFlow<String?> = MutableStateFlow(null)

    val abilities = useCase.invoke(name)

    suspend fun setName(name: String?) {
        this.name.emit(name)
    }
}