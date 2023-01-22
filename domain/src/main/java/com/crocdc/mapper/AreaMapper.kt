package com.crocdc.mapper

import com.crocdc.datadatabase.model.EncountersEntity
import com.crocdc.domain.model.Area

object AreaMapper : BaseMapper<EncountersEntity, List<Area>>() {
    override fun transform(inputModel: EncountersEntity): List<Area> =
        inputModel.encounters.map {
            Area(
                it.area,
                it.id
            )
        }
}
