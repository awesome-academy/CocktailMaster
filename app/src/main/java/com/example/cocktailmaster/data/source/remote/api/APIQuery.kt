package com.example.cocktailmaster.data.source.remote.api

import android.net.Uri
import com.example.cocktailmaster.data.source.remote.api.APIQueryExtension.appendAuthorityAndContent
import com.example.cocktailmaster.data.source.remote.utils.ParameterConstant
import com.example.cocktailmaster.data.source.remote.utils.ValueConstant

object APIQuery {

    fun querryRandomDrink() = Uri.Builder()
        .appendAuthorityAndContent(APIConstant.API_RANDOM)
        .toString()

    fun querryCategories() = Uri.Builder()
        .appendAuthorityAndContent(APIConstant.API_LIST)
        .appendQueryParameter(ParameterConstant.CATEGORY_PARAM, ValueConstant.LIST_VALUE)
        .toString()

    fun querryIngredients() = Uri.Builder()
        .appendAuthorityAndContent(APIConstant.API_LIST)
        .appendQueryParameter(ParameterConstant.INGREDIENT_PARAM, ValueConstant.LIST_VALUE)
        .toString()

    fun searchDrink(query: String) = Uri.Builder()
        .appendAuthorityAndContent(APIConstant.API_SEARCH)
        .appendQueryParameter(ParameterConstant.SEARCH_PARAM, query)
        .toString()

    fun filterByCategory(category: String) = Uri.Builder()
        .appendAuthorityAndContent(APIConstant.API_FILTER)
        .appendQueryParameter(ParameterConstant.CATEGORY_PARAM, category)
        .toString()

    fun filterByIngredient(ingredient: String) = Uri.Builder()
        .appendAuthorityAndContent(APIConstant.API_FILTER)
        .appendQueryParameter(ParameterConstant.INGREDIENT_PARAM, ingredient)
        .toString()

    fun filterByFirstLetter(firstLetter: String) = Uri.Builder()
        .appendAuthorityAndContent(APIConstant.API_SEARCH)
        .appendQueryParameter(ParameterConstant.FIRST_LETTER_PARAM, firstLetter)
        .toString()

    fun lookUpDrink(id: Int) = Uri.Builder()
        .appendAuthorityAndContent(APIConstant.API_LOOKUP)
        .appendQueryParameter(ParameterConstant.INGREDIENT_PARAM, id.toString())
        .toString()

    fun getDrinkByName(name: String) = Uri.Builder()
        .appendAuthorityAndContent(APIConstant.API_SEARCH)
        .appendQueryParameter(ParameterConstant.SEARCH_PARAM, name)
        .toString()
}
