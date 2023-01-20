package com.crocdc.mapper

import com.crocdc.datadatabase.model.PokemonSpecieEntity
import com.crocdc.domain.model.PokemonSpecie

object PokemonSpecieMapper : BaseMapper<PokemonSpecieEntity, PokemonSpecie>() {
    override fun transform(inputModel: PokemonSpecieEntity): PokemonSpecie =
        PokemonSpecie(inputModel.evolutionChain)
}