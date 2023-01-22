package com.crocdc.solidwaffle.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.crocdc.solidwaffle.R
import com.crocdc.solidwaffle.adapter.AbilityAdapter
import com.crocdc.solidwaffle.databinding.FragmentAbilitiesBinding
import com.crocdc.solidwaffle.util.viewDataBinding
import com.crocdc.solidwaffle.vm.AbilitiesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AbilitiesFragment : Fragment(R.layout.fragment_abilities) {

    private val binding: FragmentAbilitiesBinding by viewDataBinding()

    private val viewModel: AbilitiesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = AbilityAdapter()
        binding.recycler.adapter = adapter
        lifecycleScope.launch {
            viewModel.setName(checkNotNull(requireArguments().getString(ARG_NAME)))
            viewModel.abilities.collect { adapter.submitList(it) }
        }
    }

    companion object {
        private const val ARG_NAME = "name"
        fun newInstance(name: String) = AbilitiesFragment().apply {
            arguments = bundleOf(ARG_NAME to name)
        }
    }
}
