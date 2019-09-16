package com.lyazgincompany.test.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.MutableLiveData

class ConnectionStateMonitor : ConnectivityManager.NetworkCallback() {

    val networkState: MutableLiveData<NetworkState> = MutableLiveData()

    private val networkRequest: NetworkRequest = NetworkRequest.Builder().addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI).build()

    fun enable(context: Context) {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.registerNetworkCallback(networkRequest, this)
    }

    override fun onAvailable(network: Network) {
        networkState.postValue(NetworkState.Connected)
    }

    override fun onUnavailable() {
        super.onUnavailable()
        networkState.postValue(NetworkState.Lost)
    }
    enum class NetworkState{
        Connected, Lost
    }
}
