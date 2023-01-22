package com.crocdc.solidwaffle.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.crocdc.domain.model.Area
import com.crocdc.solidwaffle.databinding.ListItemAreaBinding
import com.crocdc.solidwaffle.fragments.PokemonInfoFragmentDirections

class AreaAdapter : ListAdapter<Area, AreaAdapter.ViewHolder>(DiffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ListItemAreaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ListItemAreaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(area: Area) {
            binding.txtName.text = area.name
            binding.root.setOnClickListener {
                it.findNavController().navigate(
                    PokemonInfoFragmentDirections.actionPokemonInfoFragmentToLocationAreaFragment(
                        area.id
                    )
                )
            }
        }
    }

    object DiffUtilCallback : DiffUtil.ItemCallback<Area>() {
        override fun areItemsTheSame(oldItem: Area, newItem: Area): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Area, newItem: Area): Boolean =
            oldItem == newItem
    }
}
