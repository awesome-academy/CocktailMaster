package com.example.cocktailmaster.data.repository

import com.example.cocktailmaster.data.model.Drink
import com.example.cocktailmaster.data.source.SearchDrinkDataSource
import com.example.cocktailmaster.data.source.remote.utils.RequestAPICallback

class SearchDrinkRemoteRepository private constructor(
    private val remote: SearchDrinkDataSource
) : SearchDrinkDataSource {

    override fun searchDrinks(query: String, callback: RequestAPICallback<List<Drink>>) {
        remote.searchDrinks(query, callback)
    }

    companion object {
        private var instance: SearchDrinkRemoteRepository? = null

        fun getInstace(searchDrinkDataSource: SearchDrinkDataSource) =
            instance ?: SearchDrinkRemoteRepository(searchDrinkDataSource).also { instance = it }
    }
}
