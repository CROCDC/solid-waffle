package com.crocdc.modelnetworking

import com.squareup.moshi.Json

data class Encounter(
    @Json(name = "location_area") val locationArea: LocationArea
)
