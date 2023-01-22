package com.crocdc.modelnetworking

abstract class UrlResource() {
    abstract val url: String
    fun urlToId(): String {
        val split = url.split("/")
        return split[split.size - 2]
    }
}