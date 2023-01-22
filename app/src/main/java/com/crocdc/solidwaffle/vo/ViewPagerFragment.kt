package com.crocdc.solidwaffle.vo

import com.crocdc.solidwaffle.R

sealed class ViewPagerFragment {
    abstract val title: Int

    class Evolution : ViewPagerFragment() {
        override val title: Int = R.string.evolution
    }

    data class Moves(val name: String) : ViewPagerFragment() {
        override val title: Int = R.string.moves
    }

    data class Abilities(val name: String) : ViewPagerFragment() {
        override val title: Int = R.string.abilities
    }

    class Areas : ViewPagerFragment() {
        override val title: Int = R.string.areas
    }
}
