package com.example.cocktailmaster.data.source.remote

import com.example.cocktailmaster.APINameConstant
import com.example.cocktailmaster.ModelConstant
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
        RemoteAsysntask(callback) {
            getRandomDrinks()
        }.execute()
    }

    private fun getRandomDrinks(): List<Drink> {
        val drinks: MutableList<Drink> = ArrayList()
        for(i in 0..DRINK_REQUEST_NUMBER) {
            val jsonObject = JSONObject(httpRequestAPI(APIQuery.querryRandomDrink()))
            val jsonArray = jsonObject.optJSONArray(APINameConstant.DRINKS)
            drinks.addAll(parseToJsonArray(ModelConstant.DRINK, jsonArray))
        }
        return drinks
    }

    companion object {
        private var instance: RandomDrinkRemoteDataSource? = null

        fun getInstance() = instance ?: RandomDrinkRemoteDataSource().also { instance = it }
    }
}
