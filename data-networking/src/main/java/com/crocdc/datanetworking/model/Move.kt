package com.crocdc.datanetworking.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Move(
    val name: String,
    val url: String
)
