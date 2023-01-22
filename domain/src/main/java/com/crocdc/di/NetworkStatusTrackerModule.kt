package com.crocdc.di

import android.content.Context
import com.crocdc.util.NetworkStatusTracker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkStatusTrackerModule {

    @Provides
    @Singleton
    fun provideNetworkStatusTracker(
        @ApplicationContext context: Context
    ): NetworkStatusTracker = NetworkStatusTracker(context)
}
