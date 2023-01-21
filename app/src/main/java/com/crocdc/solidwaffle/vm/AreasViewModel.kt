package com.crocdc.solidwaffle.vm

import androidx.lifecycle.ViewModel
import com.crocdc.usecase.AreasUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AreasViewModel @Inject constructor(
    useCase: AreasUseCase
) : ViewModel(), AreasUseCase by useCase