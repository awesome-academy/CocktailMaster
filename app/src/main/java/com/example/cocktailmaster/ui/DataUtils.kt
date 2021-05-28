package com.example.cocktailmaster.ui

import com.example.cocktailmaster.data.repository.CategoryRemoteRepository
import com.example.cocktailmaster.data.repository.DrinksRemoteRepository
import com.example.cocktailmaster.data.repository.IngredientRemoteRepository
import com.example.cocktailmaster.data.source.remote.CategoryRemoteDataSource
import com.example.cocktailmaster.data.source.remote.DrinksRemoteDataSource
import com.example.cocktailmaster.data.source.remote.IngredientRemoteDataSource

object RepositoryUtils {

    fun getCategoriesRepo() =
        CategoryRemoteRepository.getInstance(CategoryRemoteDataSource.getInstace())

    fun getIngredientsRepo() =
        IngredientRemoteRepository.getInstance(IngredientRemoteDataSource.getInstance())

    fun getDrinkRepo() =
        DrinksRemoteRepository.getInstace(DrinksRemoteDataSource.getInstance())
}
