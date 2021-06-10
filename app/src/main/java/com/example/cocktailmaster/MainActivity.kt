package com.example.cocktailmaster

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cocktailmaster.databinding.ActivityMainBinding
import com.example.cocktailmaster.receiver.NetworkChangeReceiver
import com.example.cocktailmaster.receiver.OnNetworkChangeCallback
import com.example.cocktailmaster.ui.NoInternetFragment
import com.example.cocktailmaster.ui.addFragment
import com.example.cocktailmaster.ui.home.HomeFragment
import com.example.cocktailmaster.ui.replaceFragment
import com.example.cocktailmaster.utils.NetworkUtils

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(),
    OnNetworkChangeCallback {

    private val networkReceiver = NetworkChangeReceiver(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        registerReceiver(networkReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        initViews()
    }

    override fun onNetworkChange(isConnected: Boolean) {
        if (!isConnected) {
            replaceFragment(supportFragmentManager, NoInternetFragment())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(networkReceiver)
    }

    private fun initViews() {
        addFragment(
            supportFragmentManager, if (NetworkUtils.isNetworkConnected(this))
                HomeFragment() else NoInternetFragment()
        )
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, MainActivity::class.java)
    }
}
