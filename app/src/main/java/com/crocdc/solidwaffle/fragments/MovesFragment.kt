package com.crocdc.solidwaffle.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.crocdc.solidwaffle.R
import com.crocdc.solidwaffle.adapter.MoveAdapter
import com.crocdc.solidwaffle.databinding.FragmentMovesBinding
import com.crocdc.solidwaffle.util.viewDataBinding
import com.crocdc.solidwaffle.vm.PokemonInfoViewModel
import kotlinx.coroutines.launch

class MovesFragment : Fragment(R.layout.fragment_moves) {

    private val binding: FragmentMovesBinding by viewDataBinding()

    private val viewModel: PokemonInfoViewModel by viewModels({ requireParentFragment() })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = MoveAdapter()
        binding.recycler.adapter = adapter
        lifecycleScope.launch {
            viewModel.pokemonInfo.collect {
                adapter.submitList(it?.moves)
            }
        }
    }
}