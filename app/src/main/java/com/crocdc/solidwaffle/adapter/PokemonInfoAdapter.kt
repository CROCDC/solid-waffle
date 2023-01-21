package com.crocdc.solidwaffle.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.crocdc.solidwaffle.fragments.AbilitiesFragment
import com.crocdc.solidwaffle.fragments.AreasFragment
import com.crocdc.solidwaffle.fragments.EvolutionsFragment
import com.crocdc.solidwaffle.fragments.MovesFragment

class PokemonInfoAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> EvolutionsFragment.newInstance("bulbasaur")
        1 -> MovesFragment.newInstance("bulbasaur")
        2 -> AbilitiesFragment.newInstance("bulbasaur")
        3 -> AreasFragment.newInstance("bulbasaur")
        else -> throw UnsupportedOperationException()
    }
}