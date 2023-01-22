package com.crocdc.datacore.repos

import com.crocdc.datacore.offlineBoundResource
import com.crocdc.datadatabase.dao.PokemonDao
import com.crocdc.datadatabase.dao.PokemonInfoDao
import com.crocdc.datadatabase.model.Ability
import com.crocdc.datadatabase.model.LearnedAt
import com.crocdc.datadatabase.model.Move
import com.crocdc.datadatabase.model.PokemonEntity
import com.crocdc.datadatabase.model.PokemonInfoEntity
import com.crocdc.datadatabase.model.Type
import com.crocdc.dataoffline.datasource.OfflinePokemonDataSourceProvider
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfflinePokemonRepository @Inject constructor(
    private val pokemonDao: PokemonDao,
    private val pokemonInfo: PokemonInfoDao,
    private val dataSource: OfflinePokemonDataSourceProvider
) {

    fun getPokemonsListing(query: String?): Flow<List<PokemonEntity>> = offlineBoundResource(
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
            pokemonDao.deleteAll()
            pokemonDao.saveAll(
                r?.results?.map { listing ->
                    PokemonEntity(
                        listing.urlToId(),
                        listing.name
                    )
                }.orEmpty()
            )
        }
    )

    fun getPokemonInfo(name: String): Flow<PokemonInfoEntity?> = offlineBoundResource(
        query = {
            pokemonInfo.getPokemonInfoEntity(name)
        },
        fetch = {
            dataSource.getPokemonInfo(name)
        },
        saveFetchResult = { r ->
            r?.let {
                pokemonInfo.save(
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