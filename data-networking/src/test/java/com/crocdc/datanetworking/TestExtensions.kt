package com.crocdc.datanetworking

import com.crocdc.modelnetworking.Resource
import com.crocdc.modelnetworking.StatusResponse
import org.junit.Assert

fun <T> Resource<T>.assert() {
    if (status != StatusResponse.LOADING) {
        Assert.assertEquals(StatusResponse.SUCCESS, status)
        println(data)
    }
}
