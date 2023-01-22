package com.crocdc.solidwaffle.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.crocdc.domain.model.FromEvolutionTo
import com.crocdc.solidwaffle.R
import com.crocdc.solidwaffle.databinding.ListItemEvolutionBinding
import com.crocdc.solidwaffle.fetchImage
import com.crocdc.solidwaffle.fragments.PokemonInfoFragmentDirections

class EvolutionAdapter : ListAdapter<FromEvolutionTo, EvolutionAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ListItemEvolutionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ListItemEvolutionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(fromEvolutionTo: FromEvolutionTo) {
            binding.imgFrom.fetchImage(fromEvolutionTo.from.image)
            binding.imgTo.fetchImage(fromEvolutionTo.to.image)
            if (fromEvolutionTo.minLevel != null) {
                binding.txtMinLevel.text = itemView.context.getString(
                    R.string.min_level,
                    fromEvolutionTo.minLevel
                )
            } else {
                binding.txtMinLevel.isVisible = false
            }
            binding.txtFrom.text = fromEvolutionTo.from.name
            binding.txtTo.text = fromEvolutionTo.to.name
            binding.imgFrom.setOnClickListener {
                navigate(fromEvolutionTo.from.name)
            }
            binding.imgTo.setOnClickListener {
                navigate(fromEvolutionTo.to.name)
            }
        }

        private fun navigate(name: String) {
            itemView.findNavController().navigate(
                PokemonInfoFragmentDirections.actionPokemonInfoFragmentSelf(name)
            )
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<FromEvolutionTo>() {
        override fun areItemsTheSame(oldItem: FromEvolutionTo, newItem: FromEvolutionTo): Boolean =
            oldItem.minLevel == newItem.minLevel

        override fun areContentsTheSame(
            oldItem: FromEvolutionTo,
            newItem: FromEvolutionTo
        ): Boolean = oldItem == newItem
    }
}
