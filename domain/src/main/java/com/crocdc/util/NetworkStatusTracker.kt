package com.crocdc.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flatMapLatest

class NetworkStatusTracker(context: Context) {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val networkStatus: Flow<NetworkStatus> = channelFlow {
        trySend(hasConnection())
        val networkStatusCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onUnavailable() {
                trySend(hasConnection()).isSuccess
            }

            override fun onAvailable(network: Network) {
                trySend(hasConnection()).isSuccess
            }

            override fun onLost(network: Network) {
                trySend(hasConnection())
            }
        }

        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
        connectivityManager.registerNetworkCallback(request, networkStatusCallback)

        awaitClose {
            connectivityManager.unregisterNetworkCallback(networkStatusCallback)
        }
    }

    private fun hasConnection(): NetworkStatus {
        val hasConnection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager.activeNetwork
        } else {
            @Suppress("DEPRECATION")
            connectivityManager.activeNetworkInfo
        } != null

        return if (hasConnection) {
            NetworkStatus.Available
        } else {
            NetworkStatus.Unavailable
        }
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
inline fun <Result> Flow<NetworkStatus>.flatMapLatest(
    crossinline onUnavailable: suspend () -> Flow<Result>,
    crossinline onAvailable: suspend () -> Flow<Result>,
): Flow<Result> = flatMapLatest { status ->
    when (status) {
        NetworkStatus.Unavailable -> onUnavailable()
        NetworkStatus.Available -> onAvailable()
    }
}

sealed class NetworkStatus {
    object Available : NetworkStatus()
    object Unavailable : NetworkStatus()
}
