package com.crocdc.mapper

import com.crocdc.domain.model.Ability
import com.crocdc.domain.model.Move
import com.crocdc.domain.model.PokemonInfo
import com.crocdc.domain.model.Type
import com.crocdc.datadatabase.model.PokemonInfoEntity

object PokemonInfoMapper : BaseMapper<PokemonInfoEntity, PokemonInfo>() {
    override fun transform(inputModel: PokemonInfoEntity): PokemonInfo =
        PokemonInfo(
            inputModel.name,
            inputModel.image,
            inputModel.types.map { Type.valueOf(it.name.uppercase()) },
            inputModel.moves.map { Move(it.name) },
            inputModel.abilities.map { Ability(it.name) },
            inputModel.locationAreaEncounters,
            inputModel.sprite
        )
}