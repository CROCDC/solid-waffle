package com.crocdc.solidwaffle.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.crocdc.solidwaffle.R
import com.crocdc.solidwaffle.adapter.AbilityAdapter
import com.crocdc.solidwaffle.databinding.FragmentAbilitiesBinding
import com.crocdc.solidwaffle.databinding.FragmentEvolutionsBinding
import com.crocdc.solidwaffle.util.viewDataBinding
import com.crocdc.solidwaffle.vm.PokemonInfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AbilitiesFragment : Fragment(R.layout.fragment_abilities) {

    private val binding: FragmentAbilitiesBinding by viewDataBinding()

    private val viewModel: PokemonInfoViewModel by viewModels({ requireParentFragment() })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = AbilityAdapter()
        binding.recycler.adapter = adapter
        lifecycleScope.launch {
            viewModel.pokemonInfo.collect {
                adapter.submitList(it?.abilities)
            }
        }
    }
}