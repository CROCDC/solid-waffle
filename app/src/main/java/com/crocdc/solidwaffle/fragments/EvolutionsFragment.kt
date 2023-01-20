package com.crocdc.solidwaffle.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.crocdc.solidwaffle.R
import com.crocdc.solidwaffle.databinding.FragmentEvolutionsBinding
import com.crocdc.solidwaffle.util.viewDataBinding
import com.crocdc.solidwaffle.vm.PokemonInfoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EvolutionsFragment : Fragment(R.layout.fragment_evolutions) {

    private val binding: FragmentEvolutionsBinding by viewDataBinding()

    private val viewModel: PokemonInfoViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}