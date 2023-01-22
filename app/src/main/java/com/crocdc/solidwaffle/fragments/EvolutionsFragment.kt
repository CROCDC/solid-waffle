package com.crocdc.solidwaffle.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.crocdc.solidwaffle.R
import com.crocdc.solidwaffle.adapter.EvolutionAdapter
import com.crocdc.solidwaffle.databinding.FragmentEvolutionsBinding
import com.crocdc.solidwaffle.util.viewDataBinding
import com.crocdc.solidwaffle.vm.PokemonInfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EvolutionsFragment : Fragment(R.layout.fragment_evolutions) {

    private val binding: FragmentEvolutionsBinding by viewDataBinding()

    private val viewModel: PokemonInfoViewModel by viewModels({ requireParentFragment() })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = EvolutionAdapter()
        binding.recycler.adapter = adapter
        lifecycleScope.launch {
            viewModel.evolutions.collect { adapter.submitList(it) }
        }
    }
}
