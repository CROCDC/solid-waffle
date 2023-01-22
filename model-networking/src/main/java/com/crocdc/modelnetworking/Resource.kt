package com.crocdc.modelnetworking

data class Resource<T>(val data: T?, val status: StatusResponse) {

    companion object {
        fun <T> error() = Resource<T>(null, StatusResponse.ERROR)
        fun <T> success(data: T?) = Resource(data, StatusResponse.SUCCESS)
    }
}

enum class StatusResponse {
    SUCCESS, LOADING, ERROR
}
