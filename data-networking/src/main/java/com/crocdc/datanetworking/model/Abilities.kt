package com.crocdc.datanetworking.model

import com.squareup.moshi.Json

data class Abilities(
    val ability: NamedApiResource,
    @Json(name = "is_hidden")
    val isHidden: Boolean
)
