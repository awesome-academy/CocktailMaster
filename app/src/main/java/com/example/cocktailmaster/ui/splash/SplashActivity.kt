@file:Suppress("DEPRECATION")

package com.example.cocktailmaster.ui.splash

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.cocktailmaster.MainActivity

const val TIME_PERIOD = 1000L
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler().postDelayed({
            startActivity(MainActivity.getIntent(this))
            finish()
        }, TIME_PERIOD)
    }
}
