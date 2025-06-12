package com.example.modul5.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.util.Log
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

interface ConnectivityObserver {
    fun observe(): Flow<Boolean>
}

class NetworkConnectivityObserver(
    private val context: Context
) : ConnectivityObserver {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun observe(): Flow<Boolean> {
        return callbackFlow {
            val callback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    Log.d("ConnectivityObserver", "Jaringan Tersedia")
                    launch { send(true) }
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    Log.d("ConnectivityObserver", "Jaringan Hilang")
                    launch { send(false) }
                }
            }

            val isConnected = connectivityManager.activeNetwork != null
            Log.d("ConnectivityObserver", "Status awal koneksi: $isConnected")
            launch { send(isConnected) }

            connectivityManager.registerDefaultNetworkCallback(callback)
            awaitClose {
                connectivityManager.unregisterNetworkCallback(callback)
            }
        }.distinctUntilChanged()
    }
}