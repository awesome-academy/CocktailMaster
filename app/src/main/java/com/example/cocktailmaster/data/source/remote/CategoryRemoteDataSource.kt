package com.example.cocktailmaster.data.source.remote

import com.example.cocktailmaster.data.model.Category
import com.example.cocktailmaster.data.source.CategoryDataSource
import com.example.cocktailmaster.data.source.remote.api.APIQuery
import com.example.cocktailmaster.data.source.remote.utils.RemoteAsysntask
import com.example.cocktailmaster.data.source.remote.utils.RequestAPICallback
import com.example.cocktailmaster.data.source.remote.utils.httpRequestAPI
import com.example.cocktailmaster.data.source.remote.utils.parseToJsonArray
import com.example.cocktailmaster.utils.APINameConstant
import com.example.cocktailmaster.utils.ModelConstant
import org.json.JSONObject

@Suppress("DEPRECATION")
class CategoryRemoteDataSource private constructor() : CategoryDataSource {
    override fun getCategories(callback: RequestAPICallback<List<Category>>) {
        RemoteAsysntask(callback) {
            getCategories()
        }.execute()
    }

    private fun getCategories(): List<Category> {
        val jsonObject = JSONObject(httpRequestAPI(APIQuery.querryCategories()))
        val jsonArray = jsonObject.optJSONArray(APINameConstant.DRINKS)
        return parseToJsonArray(ModelConstant.CATEGORY, jsonArray)
    }

    companion object {
        private var instance: CategoryRemoteDataSource? = null

        fun getInstace() = instance ?: CategoryRemoteDataSource().also { instance = it }
    }
}
