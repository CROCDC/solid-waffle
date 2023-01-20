package com.crocdc.datanetworking.model

class NamedApiResource(val url: String, val name: String) {

    fun urlToId(): String {
        val split = url.split("/")
        return split[split.size - 2]
    }
}