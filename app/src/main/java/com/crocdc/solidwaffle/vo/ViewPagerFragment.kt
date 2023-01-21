package com.crocdc.solidwaffle.vo

sealed class ViewPagerFragment {
    abstract val name: String

    data class Evolution(override val name: String) : ViewPagerFragment()
    data class Moves(override val name: String) : ViewPagerFragment()
    data class Abilities(override val name: String) : ViewPagerFragment()
    data class Areas(override val name: String) : ViewPagerFragment()
}