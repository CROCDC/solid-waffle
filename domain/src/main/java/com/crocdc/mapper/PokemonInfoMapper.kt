package com.crocdc.mapper

import com.crocdc.datacore.model.Ability
import com.crocdc.datacore.model.Move
import com.crocdc.datacore.model.PokemonInfo
import com.crocdc.datacore.model.Type
import com.crocdc.datadatabase.model.PokemonInfoEntity

object PokemonInfoMapper : BaseMapper<PokemonInfoEntity, PokemonInfo>() {
    override fun transform(inputModel: PokemonInfoEntity): PokemonInfo =
        PokemonInfo(
            inputModel.name,
            inputModel.image,
            inputModel.types.map { Type.valueOf(it.name.uppercase()) },
            inputModel.moves.map { Move(it.name) },
            inputModel.abilities.map { Ability(it.name) },
            inputModel.locationAreaEncounters
        )
}