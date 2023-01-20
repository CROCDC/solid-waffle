package com.crocdc.solidwaffle.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.crocdc.domain.model.Ability
import com.crocdc.solidwaffle.databinding.ListItemAbilityBinding

class AbilityAdapter : ListAdapter<Ability, AbilityAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ListItemAbilityBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ListItemAbilityBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(ability: Ability) {
            binding.txtName.text = ability.name
            binding.checkbox.isChecked = ability.isHidden
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<Ability>() {
        override fun areItemsTheSame(oldItem: Ability, newItem: Ability): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(
            oldItem: Ability,
            newItem: Ability
        ): Boolean = oldItem == newItem
    }
}