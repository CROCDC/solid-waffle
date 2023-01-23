package com.crocdc.datanetworking

import com.crocdc.modelnetworking.Resource
import com.squareup.moshi.Moshi
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.reflect.ParameterizedType

inline fun <reified T> OkHttpClient.execute(
    moshi: Moshi,
    builder: HttpUrl.Builder,
    type: ParameterizedType? = null
): Resource<T> {
    val response = newCall(Request.Builder().url(builder.build()).build()).execute()
    val json = response.body?.source()
    return if (response.code == 200 && json != null) {
        Resource.success(
            if (type != null) {
                moshi.adapter<T>(type).fromJson(json)
            } else {
                moshi.adapter(T::class.java).fromJson(json)
            }
        )
    } else {
        Resource.error()
    }
}
