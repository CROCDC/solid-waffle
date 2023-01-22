package com.crocdc.solidwaffle.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.crocdc.domain.model.PokemonEncounter
import com.crocdc.solidwaffle.databinding.ListItemPokemonEncounterBinding
import com.crocdc.solidwaffle.fetchImage
import com.crocdc.solidwaffle.fragments.LocationAreaFragmentDirections

class PokemonEncounterAdapter :
    ListAdapter<PokemonEncounter, PokemonEncounterAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ListItemPokemonEncounterBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ListItemPokemonEncounterBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemonEncounter: PokemonEncounter) {
            binding.img.fetchImage(pokemonEncounter.image)
            binding.txtName.text = pokemonEncounter.name
            binding.root.setOnClickListener {
                it.findNavController().navigate(
                    LocationAreaFragmentDirections.actionLocationAreaFragmentToPokemonInfoFragment(
                        pokemonEncounter.name
                    )
                )
            }
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<PokemonEncounter>() {
        override fun areItemsTheSame(
            oldItem: PokemonEncounter,
            newItem: PokemonEncounter
        ): Boolean = oldItem.name == newItem.name

        override fun areContentsTheSame(
            oldItem: PokemonEncounter,
            newItem: PokemonEncounter
        ): Boolean = oldItem == newItem
    }
}
