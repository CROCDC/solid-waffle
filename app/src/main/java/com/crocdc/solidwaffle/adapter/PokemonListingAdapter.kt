package com.crocdc.solidwaffle.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.crocdc.datacore.model.Pokemon
import com.crocdc.solidwaffle.databinding.ListItemPokemonBinding
import com.crocdc.solidwaffle.fetchImage
import com.crocdc.solidwaffle.fragments.PokemonListingFragmentDirections

class PokemonListingAdapter :
    ListAdapter<Pokemon, PokemonListingAdapter.PokemonViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder =
        PokemonViewHolder(
            ListItemPokemonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PokemonViewHolder(
        private val binding: ListItemPokemonBinding
    ) : ViewHolder(binding.root) {

        fun bind(pokemon: Pokemon) {
            binding.txt.text = pokemon.name
            binding.img.fetchImage(pokemon.image)
            binding.root.setOnClickListener {
                it.findNavController().navigate(
                    PokemonListingFragmentDirections.actionPokemonListingFragmentToPokemonInfoFragment(
                        pokemon.name
                    )
                )
            }
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
            oldItem == newItem
    }
}