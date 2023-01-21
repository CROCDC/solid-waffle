package com.crocdc.solidwaffle.vm

import androidx.lifecycle.ViewModel
import com.crocdc.usecase.MovesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovesViewModel @Inject constructor(
    useCase: MovesUseCase
) : ViewModel(), MovesUseCase by useCase