package com.example.cocktailmaster.data.repository

import com.example.cocktailmaster.data.model.Ingredient
import com.example.cocktailmaster.data.source.IngredientDataSource
import com.example.cocktailmaster.data.source.remote.utils.RequestAPICallback

class IngredientRemoteRepository private constructor(
    private val ingredientRemote: IngredientDataSource
) : IngredientDataSource {

    override fun getIngredients(callback: RequestAPICallback<List<Ingredient>>) {
        ingredientRemote.getIngredients(callback)
    }

    companion object {
        private var instance: IngredientRemoteRepository? = null

        fun getInstance(ingredientRemote: IngredientDataSource) = instance ?:
        IngredientRemoteRepository(ingredientRemote).also { instance =  it}
    }
}
