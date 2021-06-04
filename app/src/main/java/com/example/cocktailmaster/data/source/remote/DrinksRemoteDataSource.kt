package com.example.cocktailmaster.data.source.remote

import com.example.cocktailmaster.data.model.Drink
import com.example.cocktailmaster.data.source.DrinksDataSource
import com.example.cocktailmaster.data.source.remote.api.APIQuery
import com.example.cocktailmaster.data.source.remote.utils.*
import com.example.cocktailmaster.utils.APINameConstant
import com.example.cocktailmaster.utils.ModelConstant
import org.json.JSONObject

@Suppress("DEPRECATION")
class DrinksRemoteDataSource private constructor() : DrinksDataSource {
    override fun searchDrinks(query: String, callback: RequestAPICallback<List<Drink>>) {
        RemoteAsysntask(callback) {
            getListDrinkSearch(query)
        }.execute()
    }

    override fun getRandomDrinks(callback: RequestAPICallback<Drink>) {
        RemoteRandomDrinkAsynctask(callback) {
            getRandomDrinks()[0]
        }.execute()
    }

    override fun filterDrinkByCategory(
        category: String,
        callback: RequestAPICallback<List<Drink>>
    ) {
        RemoteAsysntask(callback) {
            getListDrinkByCategory(category)
        }.execute()
    }

    override fun filterDrinkByIngredient(
        ingredient: String,
        callback: RequestAPICallback<List<Drink>>
    ) {
        RemoteAsysntask(callback) {
            getListDrinkByIngredient(ingredient)
        }.execute()
    }

    override fun filterDrinkByFirstLetter(
        letter: String,
        callback: RequestAPICallback<List<Drink>>
    ) {
        RemoteAsysntask(callback) {
            getListDrinkByFirstLetter(letter)
        }.execute()
    }

    private fun getRandomDrinks(): List<Drink> {
        val jsonObject = JSONObject(httpRequestAPI(APIQuery.querryRandomDrink()))
        val jsonArray = jsonObject.optJSONArray(APINameConstant.DRINKS)
        return parseToJsonArray(ModelConstant.DRINK, jsonArray)
    }

    private fun getListDrinkSearch(queryText: String): List<Drink> {
        val jsonObject = JSONObject(httpRequestAPI(APIQuery.searchDrink(queryText)))
        val jsonArray = jsonObject.optJSONArray(APINameConstant.DRINKS)
        return parseToJsonArray(ModelConstant.DRINK, jsonArray)
    }

    private fun getListDrinkByCategory(category: String): List<Drink> {
        val jsonObject = JSONObject(httpRequestAPI(APIQuery.filterByCategory(category)))
        val jsonArray = jsonObject.optJSONArray(APINameConstant.DRINKS)
        return parseToJsonArray(ModelConstant.DRINK, jsonArray)
    }

    private fun getListDrinkByIngredient(ingredient: String): List<Drink> {
        val jsonObject = JSONObject(httpRequestAPI(APIQuery.filterByIngredient(ingredient)))
        val jsonArray = jsonObject.optJSONArray(APINameConstant.DRINKS)
        return parseToJsonArray(ModelConstant.DRINK, jsonArray)
    }

    private fun getListDrinkByFirstLetter(firstLetter: String): List<Drink> {
        val jsonObject = JSONObject(httpRequestAPI(APIQuery.filterByFirstLetter(firstLetter)))
        val jsonArray = jsonObject.optJSONArray(APINameConstant.DRINKS)
        return parseToJsonArray(ModelConstant.DRINK, jsonArray)
    }

    companion object {
        private var instance: DrinksRemoteDataSource? = null

        fun getInstance() = instance ?: DrinksRemoteDataSource().also { instance = it }
    }
}
