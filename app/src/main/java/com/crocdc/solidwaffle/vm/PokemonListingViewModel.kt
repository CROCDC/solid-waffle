package com.crocdc.solidwaffle.vm

import androidx.lifecycle.ViewModel
import com.crocdc.usecase.PokemonListingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonListingViewModel @Inject constructor(
    useCase: PokemonListingUseCase
) : ViewModel(), PokemonListingUseCase by useCase
