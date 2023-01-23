package com.crocdc.mapper

import com.crocdc.datadatabase.model.LocationAreaEntity
import com.crocdc.domain.model.LocationArea
import com.crocdc.domain.model.PokemonEncounter

object LocationAreaMapper : BaseMapper<LocationAreaEntity, LocationArea>() {
    override fun transform(inputModel: LocationAreaEntity): LocationArea =
        LocationArea(
            inputModel.name,
            inputModel.pokemonEncounters.map {
                PokemonEncounter(
                    it.pokemon.name,
                    "https://raw.githubusercontent.com/PokeAPI" +
                        "/sprites/master/sprites/pokemon/${it.pokemon.id}.png"
                )
            }
        )
}
