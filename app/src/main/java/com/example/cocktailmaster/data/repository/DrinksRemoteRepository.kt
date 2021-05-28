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

    companion object {
        private var instance: DrinksRemoteRepository? = null

        fun getInstace(drinksDataSource: DrinksDataSource) =
            instance ?: DrinksRemoteRepository(drinksDataSource).also { instance = it }
    }
}
