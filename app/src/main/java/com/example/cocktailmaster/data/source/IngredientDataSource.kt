package com.example.cocktailmaster.data.source

import com.example.cocktailmaster.data.model.Ingredient
import com.example.cocktailmaster.data.source.remote.utils.RequestAPICallback

interface IngredientDataSource {
    fun getIngredients(callback: RequestAPICallback<List<Ingredient>>)
}

