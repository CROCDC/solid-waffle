package com.crocdc.datanetworking.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideOkhttp(): OkHttpClient = OkHttpClient()

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()
}