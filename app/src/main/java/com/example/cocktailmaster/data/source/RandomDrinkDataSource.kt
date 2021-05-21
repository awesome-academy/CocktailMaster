package com.example.cocktailmaster.data.source

import com.example.cocktailmaster.data.model.Drink
import com.example.cocktailmaster.data.source.remote.utils.RequestAPICallback

interface RandomDrinkDataSource {
    fun getRandomDrinks(callback: RequestAPICallback<List<Drink>>)
}
