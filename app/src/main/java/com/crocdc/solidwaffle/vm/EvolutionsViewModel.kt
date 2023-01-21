package com.crocdc.solidwaffle.vm

import androidx.lifecycle.ViewModel
import com.crocdc.usecase.EvolutionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EvolutionsViewModel @Inject constructor(
    useCase: EvolutionsUseCase
) : ViewModel(), EvolutionsUseCase by useCase
