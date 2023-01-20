package com.crocdc.datanetworking.model

abstract class UrlResource() {
    abstract val url: String
    fun urlToId(): String {
        val split = url.split("/")
        return split[split.size - 2]
    }
}