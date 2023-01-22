package com.crocdc.solidwaffle.adapter

import android.annotation.SuppressLint
import android.util.Log
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

    @SuppressLint("NotifyDataSetChanged")
    fun setFragments(list: List<ViewPagerFragment>) {
        fragments = list
        Log.e("CROCDC", list.toString())
        notifyDataSetChanged()
    }

    override fun getItemId(position: Int): Long = fragments[position].title.toLong()

    override fun createFragment(position: Int): Fragment =
        when (val fragment = fragments.getOrNull(position)) {
            is ViewPagerFragment.Moves -> MovesFragment.newInstance(fragment.name)
            is ViewPagerFragment.Evolution -> EvolutionsFragment()
            is ViewPagerFragment.Areas -> AreasFragment()
            is ViewPagerFragment.Abilities -> AbilitiesFragment.newInstance(fragment.name)
            null -> throw UnsupportedOperationException()
        }
}
