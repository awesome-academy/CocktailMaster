package com.example.cocktailmaster.data.source

import com.example.cocktailmaster.data.model.Drink
import com.example.cocktailmaster.data.source.local.utils.OnLocalDataCallback
import com.example.cocktailmaster.data.source.remote.utils.RequestAPICallback

interface DrinksDataSource {

    interface Local {
        fun insertDrink(drink: Drink, callback: OnLocalDataCallback<Unit>)
        fun getAllFavouriteDrinks(callback: OnLocalDataCallback<List<Drink>>)
        fun isFavourite(id: Int, callback: OnLocalDataCallback<Boolean>)
        fun removeFavouriteDrink(id: Int, callback: OnLocalDataCallback<Unit>)
    }

    interface Remote {
        fun searchDrinks(query: String, callback: RequestAPICallback<List<Drink>>)
        fun getRandomDrinks(callback: RequestAPICallback<Drink>)
        fun filterDrinkByCategory(category: String, callback: RequestAPICallback<List<Drink>>)
        fun filterDrinkByIngredient(ingredient: String, callback: RequestAPICallback<List<Drink>>)
        fun filterDrinkByFirstLetter(letter: String, callback: RequestAPICallback<List<Drink>>)
        fun getDrinkById(id: Int, callback: RequestAPICallback<Drink>)
        fun getDrinkByName(name: String, callback: RequestAPICallback<List<Drink>>)
    }
}
