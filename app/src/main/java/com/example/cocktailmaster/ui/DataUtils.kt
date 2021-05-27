package com.example.cocktailmaster.ui

import com.example.cocktailmaster.data.repository.CategoryRemoteRepository
import com.example.cocktailmaster.data.repository.IngredientRemoteRepository
import com.example.cocktailmaster.data.repository.RandomDrinksRemoteRepository
import com.example.cocktailmaster.data.repository.SearchDrinkRemoteRepository
import com.example.cocktailmaster.data.source.remote.CategoryRemoteDataSource
import com.example.cocktailmaster.data.source.remote.IngredientRemoteDataSource
import com.example.cocktailmaster.data.source.remote.RandomDrinkRemoteDataSource
import com.example.cocktailmaster.data.source.remote.SearchDrinkRemoteDataSource

object RepositoryUtils {
    fun getRandomDrinksRepo() =
        RandomDrinksRemoteRepository.getInstace(RandomDrinkRemoteDataSource.getInstance())

    fun getCategoriesRepo() =
        CategoryRemoteRepository.getInstance(CategoryRemoteDataSource.getInstace())

    fun getIngredientsRepo() =
        IngredientRemoteRepository.getInstance(IngredientRemoteDataSource.getInstance())

    fun getSearchDrinksRepo() =
        SearchDrinkRemoteRepository.getInstace(SearchDrinkRemoteDataSource.getInstance())
}
