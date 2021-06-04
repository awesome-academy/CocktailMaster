package com.example.cocktailmaster.data.source

import com.example.cocktailmaster.data.model.Drink
import com.example.cocktailmaster.data.source.remote.utils.RequestAPICallback

interface DrinksDataSource {
    fun searchDrinks(query: String , callback: RequestAPICallback<List<Drink>>)
    fun getRandomDrinks(callback: RequestAPICallback<Drink>)
    fun filterDrinkByCategory(category: String , callback: RequestAPICallback<List<Drink>>)
    fun filterDrinkByIngredient(ingredient: String , callback: RequestAPICallback<List<Drink>>)
    fun filterDrinkByFirstLetter(letter: String , callback: RequestAPICallback<List<Drink>>)
}
