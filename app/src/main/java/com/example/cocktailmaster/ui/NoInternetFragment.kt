package com.example.cocktailmaster.ui

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.provider.Settings
import com.example.cocktailmaster.base.BaseFragment
import com.example.cocktailmaster.databinding.FragmentNoInternetLayoutBinding
import com.example.cocktailmaster.receiver.NetworkChangeReceiver
import com.example.cocktailmaster.receiver.OnNetworkChangeCallback
import com.example.cocktailmaster.ui.home.HomeFragment
import com.example.cocktailmaster.utils.NetworkUtils

class NoInternetFragment :
    BaseFragment<FragmentNoInternetLayoutBinding>(FragmentNoInternetLayoutBinding::inflate),
    OnNetworkChangeCallback {

    private val receiver = NetworkChangeReceiver(this)
    private var isPausing = false

    override fun initViews() {
        binding?.apply {
            textCancel.setOnClickListener { activity?.finish() }
            textConnect.setOnClickListener { startActivity(Intent(Settings.ACTION_WIFI_SETTINGS)); }
        }
    }

    @Suppress("DEPRECATION")
    override fun initData() {
        context?.registerReceiver(receiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onResume() {
        super.onResume()
        if (NetworkUtils.isNetworkConnected(context)) {
            fragmentManager?.let {
                if (it.backStackEntryCount > 1) popFragment(it, this)
                else replaceFragment(it, HomeFragment())
            }
        }
    }

    override fun onNetworkChange(isConnected: Boolean) {
        if (isConnected && !isPausing) {
            fragmentManager?.let {
                if (it.backStackEntryCount > 1) popFragment(it, this)
                else replaceFragment(it, HomeFragment())
            }
        }
    }

    override fun onPause() {
        super.onPause()
        isPausing = true
    }

    override fun onDestroy() {
        super.onDestroy()
        context?.unregisterReceiver(receiver)
    }
}
