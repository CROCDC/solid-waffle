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
import com.crocdc.solidwaffle.adapter.MoveAdapter
import com.crocdc.solidwaffle.databinding.FragmentMovesBinding
import com.crocdc.solidwaffle.util.viewDataBinding
import com.crocdc.solidwaffle.vm.MovesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovesFragment : Fragment(R.layout.fragment_moves) {

    private val binding: FragmentMovesBinding by viewDataBinding()

    private val viewModel: MovesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = MoveAdapter()
        binding.recycler.adapter = adapter
        lifecycleScope.launch {
            viewModel.setName(checkNotNull(requireArguments().getString(ARG_NAME)))
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.moves.collect { adapter.submitList(it) }
            }
        }
    }

    companion object {
        private const val ARG_NAME = "name"
        fun newInstance(name: String) = MovesFragment().apply {
            arguments = bundleOf(ARG_NAME to name)
        }
    }
}
