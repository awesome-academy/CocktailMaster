package com.example.cocktailmaster.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.cocktailmaster.utils.NetworkUtils

class NetworkChangeReceiver(
    private val callback: OnNetworkChangeCallback
) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        callback.onNetworkChange(NetworkUtils.isNetworkConnected(context))
    }
}
