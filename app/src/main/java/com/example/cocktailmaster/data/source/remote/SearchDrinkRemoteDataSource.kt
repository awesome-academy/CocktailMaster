package com.example.cocktailmaster.data.source.remote

import com.example.cocktailmaster.data.model.Drink
import com.example.cocktailmaster.data.source.SearchDrinkDataSource
import com.example.cocktailmaster.data.source.remote.api.APIQuery
import com.example.cocktailmaster.data.source.remote.utils.RemoteAsysntask
import com.example.cocktailmaster.data.source.remote.utils.RequestAPICallback
import com.example.cocktailmaster.data.source.remote.utils.httpRequestAPI
import com.example.cocktailmaster.data.source.remote.utils.parseToJsonArray
import com.example.cocktailmaster.utils.APINameConstant
import com.example.cocktailmaster.utils.ModelConstant
import org.json.JSONObject

@Suppress("DEPRECATION")
class SearchDrinkRemoteDataSource private constructor() : SearchDrinkDataSource {
    override fun searchDrinks(query: String, callback: RequestAPICallback<List<Drink>>) {
        RemoteAsysntask(callback) {
            getListDrinkSearch(query)
        }.execute()
    }

    private fun getListDrinkSearch(queryText: String): List<Drink> {
        val jsonObject = JSONObject(httpRequestAPI(APIQuery.searchDrink(queryText)))
        val jsonArray = jsonObject.optJSONArray(APINameConstant.DRINKS)
        return parseToJsonArray(ModelConstant.DRINK, jsonArray)
    }

    companion object {
        private var instance: SearchDrinkRemoteDataSource? = null

        fun getInstance() = instance ?: SearchDrinkRemoteDataSource().also { instance = it }
    }
}
