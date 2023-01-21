package com.crocdc.mapper

import com.crocdc.datadatabase.model.Move
import com.crocdc.domain.model.PokemonMove

object PokemonMoveMapper : BaseMapper<Move, PokemonMove>() {
    override fun transform(inputModel: Move): PokemonMove =
        PokemonMove(
            inputModel.name,
            inputModel.learnedAt
        )
}
