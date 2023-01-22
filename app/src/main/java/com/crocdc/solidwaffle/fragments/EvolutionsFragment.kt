package com.crocdc.solidwaffle.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.crocdc.solidwaffle.R
import com.crocdc.solidwaffle.adapter.EvolutionAdapter
import com.crocdc.solidwaffle.databinding.FragmentEvolutionsBinding
import com.crocdc.solidwaffle.util.viewDataBinding
import com.crocdc.solidwaffle.vm.EvolutionsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EvolutionsFragment : Fragment(R.layout.fragment_evolutions) {

    private val binding: FragmentEvolutionsBinding by viewDataBinding()

    private val viewModel: EvolutionsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = EvolutionAdapter()
        binding.recycler.adapter = adapter
        lifecycleScope.launch {
            viewModel.setName(checkNotNull(requireArguments().getString(ARG_NAME)))
            viewModel.evolutions.collect { adapter.submitList(it) }
        }
    }

    companion object {
        private const val ARG_NAME = "name"
        fun newInstance(name: String) = EvolutionsFragment().apply {
            arguments = bundleOf(ARG_NAME to name)
        }
    }
}
