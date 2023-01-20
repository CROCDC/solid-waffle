package com.crocdc.solidwaffle.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.crocdc.domain.model.FromEvolutionTo
import com.crocdc.solidwaffle.databinding.ListItemEvolutionBinding
import com.crocdc.solidwaffle.fetchImage

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
            binding.txtMinLevel.text = fromEvolutionTo.minLevel.toString()
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