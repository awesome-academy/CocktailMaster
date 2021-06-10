package com.example.cocktailmaster.data.source.remote

import com.example.cocktailmaster.data.model.Drink
import com.example.cocktailmaster.data.source.DrinksDataSource
import com.example.cocktailmaster.data.source.remote.api.APIQuery
import com.example.cocktailmaster.data.source.remote.utils.*
import com.example.cocktailmaster.utils.APINameConstant
import com.example.cocktailmaster.utils.ModelConstant
import org.json.JSONException
import org.json.JSONObject

@Suppress("DEPRECATION")
class DrinksRemoteDataSource private constructor() : DrinksDataSource.Remote {
    override fun searchDrinks(query: String, callback: RequestAPICallback<List<Drink>>) {
        RemoteAsysntask(callback) {
            getListDrinkSearch(query)
        }.execute()
    }

    override fun getRandomDrinks(callback: RequestAPICallback<Drink>) {
        RemoteRandomDrinkAsynctask(callback) {
            getRandomDrinks()?.get(0)
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

    override fun getDrinkById(id: Int, callback: RequestAPICallback<Drink>) {
        RemoteAsysntask(callback) {
            getDinkById(id)?.get(0)
        }.execute()
    }

    override fun getDrinkByName(name: String, callback: RequestAPICallback<List<Drink>>) {
        RemoteAsysntask(callback) {
            getDrinkByName(name)
        }.execute()
    }

    private fun getRandomDrinks(): List<Drink>? {
        return try {
            val jsonObject = JSONObject(httpRequestAPI(APIQuery.querryRandomDrink()))
            val jsonArray = jsonObject.optJSONArray(APINameConstant.DRINKS)
            jsonArray?.let { parseToJsonArray(ModelConstant.DRINK, it) }
        } catch (e: JSONException) {
            e.printStackTrace()
            null
        }
    }

    private fun getListDrinkSearch(queryText: String): List<Drink>? {
        return try {
            val jsonObject = JSONObject(httpRequestAPI(APIQuery.searchDrink(queryText)))
            val jsonArray = jsonObject.optJSONArray(APINameConstant.DRINKS)
            jsonArray?.let { parseToJsonArray(ModelConstant.DRINK, it) }
        } catch (e: JSONException) {
            e.printStackTrace()
            null
        }
    }

    private fun getListDrinkByCategory(category: String): List<Drink>? {
        return try {
            val jsonObject = JSONObject(httpRequestAPI(APIQuery.filterByCategory(category)))
            val jsonArray = jsonObject.optJSONArray(APINameConstant.DRINKS)
            jsonArray?.let { parseToJsonArray(ModelConstant.DRINK, it) }
        } catch (e: JSONException) {
            e.printStackTrace()
            null
        }
    }

    private fun getListDrinkByIngredient(ingredient: String): List<Drink>? {
        return try {
            val jsonObject = JSONObject(httpRequestAPI(APIQuery.filterByIngredient(ingredient)))
            val jsonArray = jsonObject.optJSONArray(APINameConstant.DRINKS)
            jsonArray?.let { parseToJsonArray(ModelConstant.DRINK, it) }
        } catch (e: JSONException) {
            e.printStackTrace()
            null
        }
    }

    private fun getListDrinkByFirstLetter(firstLetter: String): List<Drink>? {
        return try {
            val jsonObject = JSONObject(httpRequestAPI(APIQuery.filterByFirstLetter(firstLetter)))
            val jsonArray = jsonObject.optJSONArray(APINameConstant.DRINKS)
            jsonArray?.let { parseToJsonArray(ModelConstant.DRINK, it) }
        } catch (e: JSONException) {
            e.printStackTrace()
            null
        }
    }

    private fun getDinkById(id: Int): List<Drink>? {
        return try {
            val jsonObject = JSONObject(httpRequestAPI(APIQuery.lookUpDrink(id)))
            val jsonArray = jsonObject.optJSONArray(APINameConstant.DRINKS)
            jsonArray?.let { parseToJsonArray(ModelConstant.DRINK, it) }
        } catch (e: JSONException) {
            e.printStackTrace()
            null
        }
    }

    private fun getDrinkByName(name: String): List<Drink>? {
        return try {
            val jsonObject = JSONObject(httpRequestAPI(APIQuery.getDrinkByName(name)))
            val jsonArray = jsonObject.optJSONArray(APINameConstant.DRINKS)
            jsonArray?.let { parseToJsonArray(ModelConstant.DRINK, it) }
        } catch (e: JSONException) {
            e.printStackTrace()
            null
        }
    }

    companion object {
        private var instance: DrinksRemoteDataSource? = null

        fun getInstance() = instance ?: DrinksRemoteDataSource().also { instance = it }
    }
}
