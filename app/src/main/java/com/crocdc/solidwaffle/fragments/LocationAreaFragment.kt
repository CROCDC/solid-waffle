package com.crocdc.solidwaffle.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.crocdc.solidwaffle.R
import com.crocdc.solidwaffle.adapter.PokemonEncounterAdapter
import com.crocdc.solidwaffle.databinding.FragmentLocationAreaBinding
import com.crocdc.solidwaffle.util.viewDataBinding
import com.crocdc.solidwaffle.vm.LocationAreaViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LocationAreaFragment : Fragment(R.layout.fragment_location_area) {

    private val binding: FragmentLocationAreaBinding by viewDataBinding()

    private val viewModel: LocationAreaViewModel by viewModels()

    private val args: LocationAreaFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = PokemonEncounterAdapter()
        binding.recycler.adapter = adapter
        lifecycleScope.launch {
            viewModel.setId(args.id)
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.locationArea.collect {
                    binding.txtName.text = it?.name
                    adapter.submitList(it?.pokemonEncounters)
                }
            }
        }
    }
}