package com.example.cocktailmaster.data.source.remote

import com.example.cocktailmaster.utils.APINameConstant
import com.example.cocktailmaster.utils.ModelConstant
import com.example.cocktailmaster.data.model.Ingredient
import com.example.cocktailmaster.data.source.IngredientDataSource
import com.example.cocktailmaster.data.source.remote.api.APIQuery
import com.example.cocktailmaster.data.source.remote.utils.RemoteAsysntask
import com.example.cocktailmaster.data.source.remote.utils.RequestAPICallback
import com.example.cocktailmaster.data.source.remote.utils.httpRequestAPI
import com.example.cocktailmaster.data.source.remote.utils.parseToJsonArray
import org.json.JSONObject

@Suppress("DEPRECATION")
class IngredientRemoteDataSource private constructor() : IngredientDataSource {
    override fun getIngredients(callback: RequestAPICallback<List<Ingredient>>) {
        RemoteAsysntask(callback) {
            getIngredients()
        }.execute()
    }

    private fun getIngredients(): List<Ingredient> {
        val jsonObject = JSONObject(httpRequestAPI(APIQuery.querryIngredients()))
        val jsonArray = jsonObject.optJSONArray(APINameConstant.DRINKS)
        return parseToJsonArray(ModelConstant.INGREDIENT, jsonArray)
    }

    companion object {
        private var instance: IngredientRemoteDataSource? = null

        fun getInstance() = instance ?: IngredientRemoteDataSource().also { instance = it }
    }
}
