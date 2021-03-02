package com.example.mytranslater.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import com.example.mytranslater.application.MyApp
import com.example.mytranslater.model.networkstatus.INetworkStatus


class AppNetworkStatus : INetworkStatus {

    private var status: Boolean = false
    private val connectivityManager = MyApp.instance
        .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private val networkRequest = NetworkRequest.Builder().build()

    override fun isOnline(): Boolean {
        return status
    }

    init {
        checkNetworkStatus()
    }

    private fun checkNetworkStatus() {
        connectivityManager.registerNetworkCallback(networkRequest,
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    status = true
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    status = false
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    status = false
                }
            })
    }
}