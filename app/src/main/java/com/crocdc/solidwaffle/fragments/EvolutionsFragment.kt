package com.crocdc.solidwaffle.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.crocdc.solidwaffle.R
import com.crocdc.solidwaffle.vm.EvolutionsViewModel

class EvolutionsFragment : Fragment() {

    companion object {
        fun newInstance() = EvolutionsFragment()
    }

    private lateinit var viewModel: EvolutionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_evolutions, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EvolutionsViewModel::class.java)
        // TODO: Use the ViewModel
    }
}