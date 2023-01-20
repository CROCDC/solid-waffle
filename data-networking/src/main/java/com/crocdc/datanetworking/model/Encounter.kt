package com.crocdc.datanetworking.model

import com.squareup.moshi.Json

data class Encounter(
    @Json(name = "location_area") val locationArea: LocationArea
)