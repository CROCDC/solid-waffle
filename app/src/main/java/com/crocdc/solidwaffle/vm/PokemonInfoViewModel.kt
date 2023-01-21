package com.crocdc.solidwaffle.vm

import androidx.lifecycle.ViewModel
import com.crocdc.delegate.AreasUseCase
import com.crocdc.delegate.PokemonInfoUseCase
import com.crocdc.domain.model.Area
import com.crocdc.domain.model.PokemonInfo
import com.crocdc.solidwaffle.vo.ViewPagerFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@HiltViewModel
class PokemonInfoViewModel @Inject constructor(
    pokemonInfoUseCase: PokemonInfoUseCase,
    areasUseCase: AreasUseCase
) : ViewModel() {

    val name: MutableStateFlow<String?> = MutableStateFlow(null)

    val pokemonInfo: Flow<PokemonInfo?> = pokemonInfoUseCase.invoke(name)

    private val areas: Flow<List<Area>> = areasUseCase.invoke(name)

    val fragments: Flow<List<ViewPagerFragment>> =
        areas.combine(pokemonInfo) { areas, pokemonName ->
            pokemonName?.let { entity ->
                listOfNotNull(
                    ViewPagerFragment.Evolution(entity.name),
                    ViewPagerFragment.Moves(entity.name),
                    ViewPagerFragment.Abilities(entity.name),
                    if (areas.isNotEmpty()) {
                        ViewPagerFragment.Areas(entity.name)
                    } else {
                        null
                    }
                )
            } ?: emptyList()
        }

    suspend fun setName(name: String?) {
        this.name.emit(name)
    }
}