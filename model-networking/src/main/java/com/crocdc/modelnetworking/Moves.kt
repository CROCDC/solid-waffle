package com.crocdc.modelnetworking

import com.squareup.moshi.Json

data class Moves(
    val move: NamedApiResource,
    @Json(name = "version_group_details") val versionGroupDetails: List<VersionGroupDetails>
)
