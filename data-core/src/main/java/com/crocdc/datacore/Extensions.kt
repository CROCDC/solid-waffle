package com.crocdc.datacore

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

// TODO improve status
inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline onFetchFailed: (Throwable) -> Unit = { Unit },
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow<ResultType> {
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(data)

        try {
            saveFetchResult(fetch())
            query()
        } catch (throwable: Throwable) {
            onFetchFailed(throwable)
            query()
        }
    } else {
        query()
    }

    emitAll(flow)
}.flowOn(Dispatchers.IO)
