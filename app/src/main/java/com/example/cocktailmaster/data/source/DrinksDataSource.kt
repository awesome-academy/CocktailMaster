package com.example.cocktailmaster.data.source

import com.example.cocktailmaster.data.model.Drink
import com.example.cocktailmaster.data.source.remote.utils.RequestAPICallback

interface DrinksDataSource {
    fun searchDrinks(query: String , callback: RequestAPICallback<List<Drink>>)
    fun getRandomDrinks(callback: RequestAPICallback<Drink>)
}
