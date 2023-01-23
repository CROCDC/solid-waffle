package com.crocdc.datacore.mapper

import com.crocdc.datadatabase.model.Ability
import com.crocdc.datadatabase.model.LearnedAt
import com.crocdc.datadatabase.model.Move
import com.crocdc.datadatabase.model.PokemonInfoEntity
import com.crocdc.datadatabase.model.Type
import com.crocdc.modelnetworking.PokemonInfo

object PokemonInfoEntityMapper : BaseMapper<PokemonInfo, PokemonInfoEntity>() {
    override fun transform(inputModel: PokemonInfo): PokemonInfoEntity = PokemonInfoEntity(
        inputModel.name,
        inputModel.types.map { Type(it.type.name) },
        inputModel.moves.map { moves ->
            Move(
                moves.move.name,
                moves.versionGroupDetails.map {
                    LearnedAt(
                        it.levelLearnedAt,
                        it.moveLearnMethod.name
                    )
                }
            )
        },
        inputModel.abilities.map { Ability(it.ability.name, it.isHidden) },
        inputModel.locationAreaEncounters,
        inputModel.sprites.other.officialArtwork.frontDefault,
        inputModel.sprites.other.officialArtwork.frontShiny
    )
}
