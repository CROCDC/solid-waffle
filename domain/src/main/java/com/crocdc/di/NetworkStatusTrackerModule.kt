package com.crocdc.di

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import com.crocdc.util.NetworkStatusTracker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkStatusTrackerModule() {

    @Provides
    @Singleton
    fun provideNetworkStatusTracker(
        @ApplicationContext context: Context
    ): NetworkStatusTracker = NetworkStatusTracker(context)
}