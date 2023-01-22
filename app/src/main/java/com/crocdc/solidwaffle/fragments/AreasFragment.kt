package com.crocdc.solidwaffle.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.crocdc.solidwaffle.R
import com.crocdc.solidwaffle.adapter.AreaAdapter
import com.crocdc.solidwaffle.databinding.FragmentAreasBinding
import com.crocdc.solidwaffle.util.viewDataBinding
import com.crocdc.solidwaffle.vm.AreasViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AreasFragment : Fragment(R.layout.fragment_areas) {

    private val binding: FragmentAreasBinding by viewDataBinding()

    private val viewModel: AreasViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = AreaAdapter()
        binding.recycler.adapter = adapter
        lifecycleScope.launch {
            viewModel.setName(checkNotNull(requireArguments().getString(ARG_NAME)))
            viewModel.areas.collect { adapter.submitList(it) }
        }
    }

    companion object {
        private const val ARG_NAME = "name"
        fun newInstance(name: String) = AreasFragment().apply {
            arguments = bundleOf(ARG_NAME to name)
        }
    }
}
