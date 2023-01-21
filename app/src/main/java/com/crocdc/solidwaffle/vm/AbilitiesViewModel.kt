package com.crocdc.solidwaffle.vm

import androidx.lifecycle.ViewModel
import com.crocdc.usecase.AbilitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AbilitiesViewModel @Inject constructor(
    useCase: AbilitiesUseCase
) : ViewModel(), AbilitiesUseCase by useCase