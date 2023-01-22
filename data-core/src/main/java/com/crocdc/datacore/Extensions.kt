package com.crocdc.datacore

import android.util.Log
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
    crossinline onFetchFailed: (Throwable) -> Unit = { },
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
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

inline fun <ResultType, RequestType> offlineBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
) = flow {
    emitAll(
        try {
            saveFetchResult(fetch())
            query()
        } catch (throwable: Throwable) {
            query()
        }
    )
}.flowOn(Dispatchers.IO)
