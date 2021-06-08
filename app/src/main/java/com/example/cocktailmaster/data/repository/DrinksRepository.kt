package com.example.cocktailmaster.data.repository

import com.example.cocktailmaster.data.model.Drink
import com.example.cocktailmaster.data.source.DrinksDataSource
import com.example.cocktailmaster.data.source.local.utils.OnLocalDataCallback
import com.example.cocktailmaster.data.source.remote.utils.RequestAPICallback

class DrinksRepository private constructor(
    private val remote: DrinksDataSource.Remote,
    private val local: DrinksDataSource.Local
) : DrinksDataSource.Remote, DrinksDataSource.Local {

    override fun searchDrinks(query: String, callback: RequestAPICallback<List<Drink>>) {
        remote.searchDrinks(query, callback)
    }

    override fun getRandomDrinks(callback: RequestAPICallback<Drink>) {
        remote.getRandomDrinks(callback)
    }

    override fun filterDrinkByCategory(
        category: String,
        callback: RequestAPICallback<List<Drink>>
    ) {
        remote.filterDrinkByCategory(category, callback)
    }

    override fun filterDrinkByIngredient(
        ingredient: String,
        callback: RequestAPICallback<List<Drink>>
    ) {
        remote.filterDrinkByIngredient(ingredient, callback)
    }

    override fun filterDrinkByFirstLetter(
        letter: String,
        callback: RequestAPICallback<List<Drink>>
    ) {
        remote.filterDrinkByFirstLetter(letter, callback)
    }

    override fun getDrinkById(id: Int, callback: RequestAPICallback<Drink>) {
        remote.getDrinkById(id, callback)
    }

    override fun insertDrink(drink: Drink, callback: OnLocalDataCallback<Unit>) {
        local.insertDrink(drink, callback)
    }

    override fun getAllFavouriteDrinks(callback: OnLocalDataCallback<List<Drink>>) {
        local.getAllFavouriteDrinks(callback)
    }

    override fun isFavourite(id: Int, callback: OnLocalDataCallback<Boolean>) {
        local.isFavourite(id, callback)
    }

    override fun removeFavouriteDrink(id: Int, callback: OnLocalDataCallback<Unit>) {
        local.removeFavouriteDrink(id, callback)
    }

    companion object {
        private var instance: DrinksRepository? = null

        fun getInstace(
            remote: DrinksDataSource.Remote,
            local: DrinksDataSource.Local) =
            instance ?: DrinksRepository(remote, local).also { instance = it }
    }
}
