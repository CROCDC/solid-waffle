package com.crocdc.solidwaffle.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.crocdc.domain.model.PokemonMove
import com.crocdc.solidwaffle.databinding.ListItemMoveBinding

class MoveAdapter : ListAdapter<PokemonMove, MoveAdapter.ViewHolder>(DiffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ListItemMoveBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ListItemMoveBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemonMove: PokemonMove) {
            binding.txtName.text = pokemonMove.name
        }
    }

    object DiffUtilCallback : DiffUtil.ItemCallback<PokemonMove>() {
        override fun areItemsTheSame(oldItem: PokemonMove, newItem: PokemonMove): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: PokemonMove, newItem: PokemonMove): Boolean =
            oldItem == newItem
    }
}
