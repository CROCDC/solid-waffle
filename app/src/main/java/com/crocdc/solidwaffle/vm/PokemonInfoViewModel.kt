package com.crocdc.solidwaffle.vm

import androidx.lifecycle.ViewModel
import com.crocdc.usecase.PokemonInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonInfoViewModel @Inject constructor(useCase: PokemonInfoUseCase) : ViewModel(),
    PokemonInfoUseCase by useCase