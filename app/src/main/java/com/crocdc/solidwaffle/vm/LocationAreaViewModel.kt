package com.crocdc.solidwaffle.vm

import androidx.lifecycle.ViewModel
import com.crocdc.domain.model.LocationArea
import com.crocdc.usecase.LocationAreaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class LocationAreaViewModel @Inject constructor(
    locationAreaUseCase: LocationAreaUseCase
) : ViewModel() {
    private val id: MutableStateFlow<String?> = MutableStateFlow(null)

    val locationArea: Flow<LocationArea?> = locationAreaUseCase.invoke(id)

    suspend fun setId(id: String) {
        this.id.emit(id)
    }
}