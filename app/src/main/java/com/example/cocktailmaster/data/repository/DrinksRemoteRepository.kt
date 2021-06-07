package com.example.cocktailmaster.data.repository

import com.example.cocktailmaster.data.model.Drink
import com.example.cocktailmaster.data.source.DrinksDataSource
import com.example.cocktailmaster.data.source.remote.utils.RequestAPICallback

class DrinksRemoteRepository private constructor(
    private val remote: DrinksDataSource
) : DrinksDataSource {

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

    companion object {
        private var instance: DrinksRemoteRepository? = null

        fun getInstace(drinksDataSource: DrinksDataSource) =
            instance ?: DrinksRemoteRepository(drinksDataSource).also { instance = it }
    }
}
