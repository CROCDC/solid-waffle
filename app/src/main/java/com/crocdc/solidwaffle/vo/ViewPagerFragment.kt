package com.crocdc.solidwaffle.vo

import com.crocdc.solidwaffle.R

sealed class ViewPagerFragment {
    abstract val name: String
    abstract val title: Int

    data class Evolution(override val name: String) : ViewPagerFragment() {
        override val title: Int = R.string.evolution
    }

    data class Moves(override val name: String) : ViewPagerFragment() {
        override val title: Int = R.string.moves
    }

    data class Abilities(override val name: String) : ViewPagerFragment() {
        override val title: Int = R.string.abilities
    }

    data class Areas(override val name: String) : ViewPagerFragment() {
        override val title: Int = R.string.areas
    }
}
