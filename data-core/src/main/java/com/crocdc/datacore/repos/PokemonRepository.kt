package com.crocdc.datacore.repos

import com.crocdc.datacore.networkBoundResource
import com.crocdc.datadatabase.dao.PokemonDao
import com.crocdc.datadatabase.dao.PokemonInfoDao
import com.crocdc.datadatabase.model.Ability
import com.crocdc.datadatabase.model.LearnedAt
import com.crocdc.datadatabase.model.Move
import com.crocdc.datadatabase.model.PokemonEntity
import com.crocdc.datadatabase.model.PokemonInfoEntity
import com.crocdc.datadatabase.model.Type
import com.crocdc.datanetworking.datasource.PokemonDataSourceProvider
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val pokemonDao: PokemonDao,
    private val pokemonInfoDao: PokemonInfoDao,
    private val dataSource: PokemonDataSourceProvider
) {

    fun getPokemonsListing(query: String?): Flow<List<PokemonEntity>> = networkBoundResource(
        query = {
            if (query == null) {
                pokemonDao.getAll()
            } else {
                pokemonDao.search(query)
            }
        },
        fetch = {
            dataSource.getPokemonsListing()
        },
        saveFetchResult = { r ->
            pokemonDao.saveAll(
                r.data?.results?.map { listing ->
                    PokemonEntity(
                        listing.urlToId(),
                        listing.name
                    )
                }.orEmpty()
            )
        },
        shouldFetch = { it.isEmpty() }
    )

    fun getPokemonInfo(name: String): Flow<PokemonInfoEntity?> = networkBoundResource(
        query = {
            pokemonInfoDao.getPokemonInfoEntity(name)
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
                        it.moves.map {
                            Move(
                                it.move.name,
                                it.versionGroupDetails.map {
                                    LearnedAt(
                                        it.levelLearnedAt,
                                        it.moveLearnMethod.name
                                    )
                                }
                            )
                        },
                        it.abilities.map { Ability(it.ability.name, it.isHidden) },
                        it.locationAreaEncounters,
                        it.sprites.other.officialArtwork.frontDefault,
                        it.sprites.other.officialArtwork.frontShiny,
                        it.sprites.frontDefault
                    )
                )
            }
        }
    )
}