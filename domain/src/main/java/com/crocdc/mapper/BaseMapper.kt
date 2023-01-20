package com.crocdc.mapper

abstract class BaseMapper<Input, Output> {

    fun transform(inputModelList: List<Input>) = inputModelList.map {
        transform(it)
    }

    abstract fun transform(inputModel: Input): Output
}
