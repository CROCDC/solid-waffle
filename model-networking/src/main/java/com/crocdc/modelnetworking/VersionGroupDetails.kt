package com.crocdc.modelnetworking

import com.squareup.moshi.Json

data class VersionGroupDetails(
    @Json(name = "level_learned_at") val levelLearnedAt: Int,
    @Json(name = "move_learn_method") val moveLearnMethod: NamedApiResource
)
