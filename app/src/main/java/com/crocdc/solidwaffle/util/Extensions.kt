package com.crocdc.solidwaffle.util

import com.crocdc.domain.model.Type
import com.crocdc.solidwaffle.R

fun Type.getColor() = when (this) {
    Type.FIRE -> R.color.fire
    Type.WATER -> R.color.water
    Type.DARK -> R.color.dark
    Type.DRAGON -> R.color.dragon
    Type.POISON -> R.color.poison
    Type.ELECTRIC -> R.color.electric
    Type.GHOST -> R.color.ghost
    Type.FLYING -> R.color.fliying
    Type.FAIRY -> R.color.fairy
    Type.PSYCHIC -> R.color.psychic
    Type.NORMAL -> R.color.normal
    Type.ROCK -> R.color.rock
    Type.GRASS -> R.color.grass
    Type.ICE -> R.color.ice
    Type.GROUND -> R.color.ground
    Type.STEEL -> R.color.steel
    Type.BUG -> R.color.bug
    Type.FIGHTING -> R.color.fight
    else -> null
}

fun Type.getIcon() = when (this) {
    Type.FIRE -> R.drawable.ic_fire
    Type.WATER -> R.drawable.ic_water
    Type.DARK -> R.drawable.ic_dark
    Type.DRAGON -> R.drawable.ic_dragon
    Type.POISON -> R.drawable.ic_poison
    Type.ELECTRIC -> R.drawable.ic_electric
    Type.GHOST -> R.drawable.ic_ghost
    Type.FLYING -> R.drawable.ic_flying
    Type.FAIRY -> R.drawable.ic_fairy
    Type.PSYCHIC -> R.drawable.ic_psychic
    Type.NORMAL -> R.drawable.ic_normal
    Type.ROCK -> R.drawable.ic_rock
    Type.GRASS -> R.drawable.ic_grass
    Type.ICE -> R.drawable.ic_ice
    Type.GROUND -> R.drawable.ic_ground
    Type.STEEL -> R.drawable.ic_steel
    Type.BUG -> R.drawable.ic_bug
    Type.FIGHTING -> R.drawable.ic_fight
    else -> null
}
