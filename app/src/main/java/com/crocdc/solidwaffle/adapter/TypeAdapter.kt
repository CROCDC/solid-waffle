package com.crocdc.solidwaffle.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.crocdc.domain.model.Type
import com.crocdc.solidwaffle.databinding.ListItemTypeBinding
import com.crocdc.solidwaffle.util.getColor
import com.crocdc.solidwaffle.util.getIcon

class TypeAdapter : ListAdapter<Type, TypeAdapter.ViewHolder>(DiffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ListItemTypeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ListItemTypeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(type: Type) {
            binding.txtName.text = type.name
            type.getIcon()?.let {
                binding.txtName.setCompoundDrawablesWithIntrinsicBounds(
                    it,
                    0,
                    0,
                    0
                )
            }
            type.getColor()?.let {
                binding.card.setCardBackgroundColor(ContextCompat.getColor(itemView.context, it))
            }
        }
    }

    object DiffUtilCallback : DiffUtil.ItemCallback<Type>() {
        override fun areItemsTheSame(oldItem: Type, newItem: Type): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Type, newItem: Type): Boolean =
            oldItem == newItem
    }
}
