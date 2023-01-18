package com.crocdc.datacore.repos

import com.crocdc.datacore.model.Pokemon
import com.crocdc.datacore.model.PokemonInfo
import com.crocdc.datacore.networkBoundResource
import com.crocdc.datadatabase.dao.PokemonDao
import com.crocdc.datadatabase.dao.PokemonInfoDao
import com.crocdc.datadatabase.model.Ability
import com.crocdc.datadatabase.model.Move
import com.crocdc.datadatabase.model.PokemonEntity
import com.crocdc.datadatabase.model.PokemonInfoEntity
import com.crocdc.datadatabase.model.Type
import com.crocdc.datanetworking.datasource.PokemonDataSourceProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val pokemonDao: PokemonDao,
    private val pokemonInfoDao: PokemonInfoDao,
    private val dataSource: PokemonDataSourceProvider
) {

    fun getPokemonsListing(query: String?): Flow<List<Pokemon>> = networkBoundResource(
        query = {
            if (query == null) {
                pokemonDao.getAll()
            } else {
                pokemonDao.search(query)
            }.map { pokemonEntities ->
                pokemonEntities.map {
                    // todo improve image get
                    Pokemon(
                        it.name,
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${it.id}.png"
                    )
                }
            }
        },
        fetch = {
            dataSource.getPokemonsListing()
        },
        saveFetchResult = { r ->
            //todo improve id
            pokemonDao.saveAll(
                r.data?.results?.map { listing ->
                    val split = listing.url.split("/")
                    PokemonEntity(
                        split.get(split.size - 2),
                        listing.name
                    )
                }.orEmpty()
            )
        },
        shouldFetch = { it.isEmpty() }
    )

    fun getPokemonInfo(name: String): Flow<PokemonInfo?> = networkBoundResource(
        query = {
            // TODO improve packages
            pokemonInfoDao.getPokemonInfoEntity(name).map {
                it?.let {
                    PokemonInfo(
                        it.name,
                        it.image,
                        it.types.map {
                            com.crocdc.datacore.model.Type.valueOf(it.name.uppercase())
                        },
                        it.moves.map { com.crocdc.datacore.model.Move(it.name) },
                        it.abilities.map { com.crocdc.datacore.model.Ability(it.name) },
                        it.locationAreaEncounters
                    )
                }
            }
        },
        fetch = {
            dataSource.getPokemonInfo(name)
        },
        saveFetchResult = { r ->
            r.data?.let {
                pokemonInfoDao.save(
                    PokemonInfoEntity(
                        it.name,
                        it.types.map { Type(it.type.name) },
                        it.moves.map { Move(it.move.name) },
                        it.abilities.map { Ability(it.ability.name) },
                        it.locationAreaEncounters,
                        it.sprites.other.officialArtwork.frontDefault
                    )
                )
            }
        }
    )
}