package com.example.cocktailmaster.data.repository

import com.example.cocktailmaster.data.model.Drink
import com.example.cocktailmaster.data.source.RandomDrinkDataSource
import com.example.cocktailmaster.data.source.remote.utils.RequestAPICallback

class RandomDrinksRemoteRepository private constructor(
    private val randomDrinkDataSource: RandomDrinkDataSource
) : RandomDrinkDataSource {

    override fun getRandomDrinks(callback: RequestAPICallback<Drink>) {
        randomDrinkDataSource.getRandomDrinks(callback)
    }

    companion object {
        private var instance: RandomDrinksRemoteRepository? = null

        fun getInstace(randomDrinkDataSource: RandomDrinkDataSource) =
            instance ?: RandomDrinksRemoteRepository(randomDrinkDataSource).also { instance = it }
    }
}
