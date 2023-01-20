package com.crocdc.solidwaffle.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.crocdc.domain.model.Move
import com.crocdc.solidwaffle.databinding.ListItemMoveBinding

class MoveAdapter : ListAdapter<Move, MoveAdapter.ViewHolder>(DiffUtilCallback) {

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
        fun bind(move: Move) {
            binding.txtName.text = move.name
        }
    }

    object DiffUtilCallback : DiffUtil.ItemCallback<Move>() {
        override fun areItemsTheSame(oldItem: Move, newItem: Move): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Move, newItem: Move): Boolean =
            oldItem == newItem
    }
}