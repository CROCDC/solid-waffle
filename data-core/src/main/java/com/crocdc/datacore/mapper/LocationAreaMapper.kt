package com.crocdc.datacore.mapper

import com.crocdc.datadatabase.model.LocationAreaEntity
import com.crocdc.datadatabase.model.PokemonArea
import com.crocdc.datadatabase.model.PokemonEncounter
import com.crocdc.modelnetworking.LocationAreaResponse

class LocationAreaMapper(
    private val id: String
) : BaseMapper<LocationAreaResponse, LocationAreaEntity>() {
    override fun transform(inputModel: LocationAreaResponse): LocationAreaEntity =
        LocationAreaEntity(
            id,
            inputModel.names.firstOrNull()?.name,
            inputModel.pokemonEncounters.map {
                PokemonEncounter(
                    PokemonArea(
                        it.pokemon.name,
                        it.pokemon.urlToId()
                    )
                )
            }
        )
}