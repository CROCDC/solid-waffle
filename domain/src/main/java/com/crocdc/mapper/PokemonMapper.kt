package com.crocdc.mapper

import com.crocdc.domain.model.Pokemon
import com.crocdc.datadatabase.model.PokemonEntity

object PokemonMapper : BaseMapper<PokemonEntity, Pokemon>() {

    override fun transform(inputModel: PokemonEntity): Pokemon =
        Pokemon(
            inputModel.name,
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${inputModel.id}.png"
        )
}
