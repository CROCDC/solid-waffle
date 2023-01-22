package com.crocdc.solidwaffle.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crocdc.domain.model.Area
import com.crocdc.domain.model.PokemonInfo
import com.crocdc.solidwaffle.vo.ImageOption
import com.crocdc.solidwaffle.vo.ViewPagerFragment
import com.crocdc.usecase.AreasUseCase
import com.crocdc.usecase.PokemonInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonInfoViewModel @Inject constructor(
    pokemonInfoUseCase: PokemonInfoUseCase,
    areasUseCase: AreasUseCase
) : ViewModel() {

    private val name: MutableStateFlow<String?> = MutableStateFlow(null)

    private val _selectedImage: MutableStateFlow<ImageOption?> = MutableStateFlow(null)

    val selectedImage: StateFlow<ImageOption?> = _selectedImage.asStateFlow()

    val pokemonInfo: Flow<PokemonInfo?> = pokemonInfoUseCase.invoke(name)

    val imageOptions = pokemonInfo.map {
        it?.let {
            listOf(
                ImageOption.OfficialArtWork(it.officialArtWork),
                ImageOption.OfficialArtWorkShiny(it.officialArtWorkShiny)
            )
        } ?: emptyList()
    }

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

    init {
        viewModelScope.launch {
            imageOptions.collect { it.firstOrNull()?.let { setSelectedImage(it) } }
        }
    }

    suspend fun setName(name: String?) {
        this.name.emit(name)
    }

    suspend fun setSelectedImage(selectedImageOption: ImageOption) {
        this._selectedImage.emit(selectedImageOption)
    }
}
