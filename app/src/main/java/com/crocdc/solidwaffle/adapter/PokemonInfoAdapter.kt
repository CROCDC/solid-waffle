package com.crocdc.solidwaffle.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.crocdc.solidwaffle.fragments.EvolutionsFragment

class PokemonInfoAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 1

    override fun createFragment(position: Int): Fragment = EvolutionsFragment()
}