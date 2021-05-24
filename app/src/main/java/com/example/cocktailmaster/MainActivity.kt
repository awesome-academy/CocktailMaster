package com.example.cocktailmaster

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cocktailmaster.databinding.ActivityMainBinding
import com.example.cocktailmaster.ui.addFragment
import com.example.cocktailmaster.ui.home.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        initViews()
    }

    private fun initViews() {
        addFragment(supportFragmentManager , HomeFragment())
    }

    companion object {
        fun getIntent(context: Context) = Intent(context , MainActivity::class.java)
    }
}
