package com.crocdc.datanetworking.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Ability(val name: String)
