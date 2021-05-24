package com.example.cocktailmaster.data.source.remote

import com.example.cocktailmaster.utils.APINameConstant
import com.example.cocktailmaster.utils.ModelConstant
import com.example.cocktailmaster.data.model.Drink
import com.example.cocktailmaster.data.source.RandomDrinkDataSource
import com.example.cocktailmaster.data.source.remote.api.APIQuery
import com.example.cocktailmaster.data.source.remote.utils.RemoteAsysntask
import com.example.cocktailmaster.data.source.remote.utils.RequestAPICallback
import com.example.cocktailmaster.data.source.remote.utils.httpRequestAPI
import com.example.cocktailmaster.data.source.remote.utils.parseToJsonArray
import org.json.JSONObject

const val DRINK_REQUEST_NUMBER = 4
@Suppress("DEPRECATION")
class RandomDrinkRemoteDataSource : RandomDrinkDataSource {
    override fun getRandomDrinks(callback: RequestAPICallback<List<Drink>>) {
        for(i in 0..DRINK_REQUEST_NUMBER) {
            RemoteAsysntask(callback) {
                getRandomDrinks()
            }.execute()
        }
    }

    private fun getRandomDrinks(): List<Drink> {
        val jsonObject = JSONObject(httpRequestAPI(APIQuery.querryRandomDrink()))
        val jsonArray = jsonObject.optJSONArray(APINameConstant.DRINKS)
        return parseToJsonArray(ModelConstant.DRINK, jsonArray)
    }

    companion object {
        private var instance: RandomDrinkRemoteDataSource? = null

        fun getInstance() = instance ?: RandomDrinkRemoteDataSource().also { instance = it }
    }
}
