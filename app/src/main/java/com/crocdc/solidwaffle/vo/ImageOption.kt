package com.crocdc.solidwaffle.vo

import com.crocdc.solidwaffle.R

sealed class ImageOption {
    abstract val title: Int
    abstract val image: String

    data class OfficialArtWork(override val image: String) : ImageOption() {
        override val title: Int = R.string.official_artwork
    }

    data class OfficialArtWorkShiny(override val image: String) : ImageOption() {
        override val title: Int = R.string.official_artwork_shiny
    }
}
