package com.crocdc.mapper

import com.crocdc.datadatabase.model.LearnedAt
import com.crocdc.datadatabase.model.PokemonInfoEntity
import com.crocdc.domain.model.Ability
import com.crocdc.domain.model.Move
import com.crocdc.domain.model.PokemonInfo
import com.crocdc.domain.model.Type

object PokemonInfoMapper : BaseMapper<PokemonInfoEntity, PokemonInfo>() {
    override fun transform(inputModel: PokemonInfoEntity): PokemonInfo =
        PokemonInfo(
            inputModel.name,
            inputModel.image,
            inputModel.types.map { Type.valueOf(it.name.uppercase()) },
            inputModel.moves.map { move ->
                Move(
                    move.name,
                    move.learnedAt.map {
                        LearnedAt(
                            it.levelLearnedAt,
                            it.moveLearnMethod
                        )
                    }
                )
            },
            inputModel.abilities.map { Ability(it.name, it.isHidden) },
            inputModel.sprite
        )
}