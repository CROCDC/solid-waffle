package com.crocdc.solidwaffle.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.crocdc.solidwaffle.fragments.AbilitiesFragment
import com.crocdc.solidwaffle.fragments.AreasFragment
import com.crocdc.solidwaffle.fragments.EvolutionsFragment
import com.crocdc.solidwaffle.fragments.MovesFragment
import com.crocdc.solidwaffle.vo.ViewPagerFragment

class PokemonInfoAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private var fragments: List<ViewPagerFragment> = emptyList()

    override fun getItemCount(): Int = fragments.size

    fun setFragments(list: List<ViewPagerFragment>) {
        fragments = list
        notifyDataSetChanged()
    }

    override fun createFragment(position: Int): Fragment =
        when (val fragment = fragments.getOrNull(position)) {
            is ViewPagerFragment.Moves -> MovesFragment.newInstance(fragment.name)
            is ViewPagerFragment.Evolution -> EvolutionsFragment.newInstance(fragment.name)
            is ViewPagerFragment.Areas -> AreasFragment.newInstance(fragment.name)
            is ViewPagerFragment.Abilities -> AbilitiesFragment.newInstance(fragment.name)
            null -> throw UnsupportedOperationException()
        }
}
