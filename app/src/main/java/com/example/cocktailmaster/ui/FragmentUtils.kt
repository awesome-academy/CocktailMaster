package com.example.cocktailmaster.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.cocktailmaster.R

fun addFragment(fragmentManager: FragmentManager, fragment: Fragment) {
    val fragmentTransaction = fragmentManager.beginTransaction()
    fragmentTransaction.apply {
        add(R.id.frameMain, fragment)
        addToBackStack(null)
        commit()
    }
}

fun replaceFragment(fragmentManager: FragmentManager, fragment: Fragment) {
    val fragmentTransaction = fragmentManager.beginTransaction()
    fragmentTransaction.apply {
        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        replace(R.id.frameMain, fragment)
        addToBackStack(null)
        commit()
    }
}

fun popFragment(fragmentManager: FragmentManager, fragment: Fragment) {
    val fragmentTransaction = fragmentManager.beginTransaction()
    fragmentTransaction.apply {
        remove(fragment)
        commit()
        fragmentManager.popBackStack()
    }
}
