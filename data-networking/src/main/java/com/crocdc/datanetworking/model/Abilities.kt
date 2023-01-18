package com.crocdc.datanetworking.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Abilities(
    val ability: Ability,
    @Json(name = "is_hidden")
    val isHidden: Boolean
)
