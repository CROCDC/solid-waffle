package com.crocdc.solidwaffle.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.crocdc.solidwaffle.R
import com.crocdc.solidwaffle.adapter.AreaAdapter
import com.crocdc.solidwaffle.databinding.FragmentAreasBinding
import com.crocdc.solidwaffle.util.viewDataBinding
import com.crocdc.solidwaffle.vm.AreasViewModel
import com.crocdc.solidwaffle.vm.PokemonInfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AreasFragment : Fragment(R.layout.fragment_areas) {

    private val binding: FragmentAreasBinding by viewDataBinding()

    private val viewModel: PokemonInfoViewModel by viewModels({ requireParentFragment() })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = AreaAdapter()
        binding.recycler.adapter = adapter
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.areas.collect { adapter.submitList(it) }
            }
        }
    }
}
