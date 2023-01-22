package com.crocdc.solidwaffle.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crocdc.domain.model.Area
import com.crocdc.domain.model.FromEvolutionTo
import com.crocdc.domain.model.PokemonInfo
import com.crocdc.solidwaffle.vo.ImageOption
import com.crocdc.solidwaffle.vo.ViewPagerFragment
import com.crocdc.usecase.AreasUseCase
import com.crocdc.usecase.EvolutionsUseCase
import com.crocdc.usecase.PokemonInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonInfoViewModel @Inject constructor(
    pokemonInfoUseCase: PokemonInfoUseCase,
    areasUseCase: AreasUseCase,
    evolutionsUseCase: EvolutionsUseCase
) : ViewModel() {

    private val name: MutableStateFlow<String?> = MutableStateFlow(null)

    private val _selectedImage: MutableStateFlow<ImageOption?> = MutableStateFlow(null)

    val selectedImage: StateFlow<ImageOption?> = _selectedImage.asStateFlow()

    val pokemonInfo: Flow<PokemonInfo?> = pokemonInfoUseCase.invoke(name)

    private val evolutions: Flow<List<FromEvolutionTo>> = evolutionsUseCase.invoke(name)

    val imageOptions = pokemonInfo.map {
        it?.let {
            listOf(
                ImageOption.OfficialArtWork(it.officialArtWork),
                ImageOption.OfficialArtWorkShiny(it.officialArtWorkShiny)
            )
        } ?: emptyList()
    }

    private val areas: Flow<List<Area>> = areasUseCase.invoke(name)

    val fragments: Flow<List<ViewPagerFragment>> = merge(areas, pokemonInfo, evolutions).map {
        getFragments(areas.first(), pokemonInfo.first(), evolutions.first())
    }

    init {
        viewModelScope.launch {
            imageOptions.collect { options -> options.firstOrNull()?.let { setSelectedImage(it) } }
        }
    }

    suspend fun setName(name: String?) {
        this.name.emit(name)
    }

    suspend fun setSelectedImage(selectedImageOption: ImageOption) {
        this._selectedImage.emit(selectedImageOption)
    }

    private fun getFragments(
        areas: List<Area>,
        info: PokemonInfo?,
        evolutions: List<FromEvolutionTo>
    ) = info?.let {
        listOfNotNull(
            if (evolutions.isNotEmpty()) {
                ViewPagerFragment.Evolution(it.name)
            } else {
                null
            },
            ViewPagerFragment.Moves(it.name),
            ViewPagerFragment.Abilities(it.name),
            if (areas.isNotEmpty()) {
                ViewPagerFragment.Areas(it.name)
            } else {
                null
            }
        )
    } ?: listOf()
}
