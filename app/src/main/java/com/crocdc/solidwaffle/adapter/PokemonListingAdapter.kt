package com.crocdc.solidwaffle.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.crocdc.datacore.model.Pokemon
import com.crocdc.solidwaffle.databinding.ListItemPokemonBinding
import com.squareup.picasso.Picasso

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
            Picasso.get()
                .load(pokemon.image)
                .into(binding.img)
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
            oldItem == newItem
    }
}