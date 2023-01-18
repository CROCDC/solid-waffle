package com.crocdc.solidwaffle.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.crocdc.datacore.model.Type
import com.crocdc.solidwaffle.R
import com.crocdc.solidwaffle.databinding.ListItemTypeBinding

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
            when (type) {
                Type.FIRE -> R.drawable.ic_fire to R.color.fire
                Type.WATER -> R.drawable.ic_water to R.color.water
                Type.DARK -> R.drawable.ic_dark to R.color.dark
                Type.DRAGON -> R.drawable.ic_dragon to R.color.dragon
                Type.POISON -> R.drawable.ic_poison to R.color.poison
                Type.ELECTRIC -> R.drawable.ic_electric to R.color.electric
                Type.GHOST -> R.drawable.ic_ghost to R.color.ghost
                Type.FLYING -> R.drawable.ic_flying to R.color.fliying
                Type.FAIRY -> R.drawable.ic_fairy to R.color.fairy
                Type.PSYCHIC -> R.drawable.ic_psychic to R.color.psychic
                Type.NORMAL -> R.drawable.ic_normal to R.color.normal
                Type.ROCK -> R.drawable.ic_rock to R.color.rock
                Type.GRASS -> R.drawable.ic_grass to R.color.grass
                Type.ICE -> R.drawable.ic_ice to R.color.ice
                Type.GROUND -> R.drawable.ic_ground to R.color.ground
                Type.STEEL -> R.drawable.ic_steel to R.color.steel
            }.run {
                changeImgAndBackground(first, second)
            }
        }

        private fun changeImgAndBackground(@DrawableRes image: Int, @ColorRes color: Int) {
            binding.img.setImageResource(image)
            binding.card.setCardBackgroundColor(ContextCompat.getColor(itemView.context, color))
        }
    }

    object DiffUtilCallback : DiffUtil.ItemCallback<Type>() {
        override fun areItemsTheSame(oldItem: Type, newItem: Type): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Type, newItem: Type): Boolean =
            oldItem == newItem
    }
}
