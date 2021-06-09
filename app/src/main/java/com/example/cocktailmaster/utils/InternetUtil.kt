package com.example.cocktailmaster.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object NetworkUtils {
    @Suppress("DEPRECATION")
    fun isNetworkConnected(context: Context?): Boolean {
        val connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val stateMobile =
            connectivityManager?.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)?.state
        val stateWifi = connectivityManager?.getNetworkInfo(ConnectivityManager.TYPE_WIFI)?.state
        return stateMobile == NetworkInfo.State.CONNECTED || stateWifi == NetworkInfo.State.CONNECTED
    }
}
