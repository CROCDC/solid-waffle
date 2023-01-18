package com.crocdc.datanetworking

import org.junit.Assert

fun <T> Resource<T>.assert() {
    if (status != StatusResponse.LOADING) {
        Assert.assertEquals(StatusResponse.SUCCESS, status)
        println(data)
    }
}