package com.example.cocktailmaster.data.source.remote.api

import android.net.Uri
import com.example.cocktailmaster.data.source.remote.utils.ParameterConstant
import com.example.cocktailmaster.data.source.remote.utils.ValueConstant

object APIQuery {

    fun querryRandomDrink() = Uri.Builder()
        .scheme(APIConstant.SCHEME_API)
        .authority(APIConstant.AUTHORITY)
        .appendPath(APIConstant.API_CONTENT)
        .appendPath(APIConstant.API_RANDOM)
        .toString()

    fun querryCategories() = Uri.Builder()
        .scheme(APIConstant.SCHEME_API)
        .authority(APIConstant.AUTHORITY)
        .appendPath(APIConstant.API_CONTENT)
        .appendPath(APIConstant.API_LIST)
        .appendQueryParameter(ParameterConstant.CATEGORY_PARAM , ValueConstant.LIST_VALUE)
        .toString()

    fun querryIngredients() = Uri.Builder()
        .scheme(APIConstant.SCHEME_API)
        .authority(APIConstant.AUTHORITY)
        .appendPath(APIConstant.API_CONTENT)
        .appendPath(APIConstant.API_LIST)
        .appendQueryParameter(ParameterConstant.INGREDIENT_PARAM , ValueConstant.LIST_VALUE)
        .toString()

    fun searchDrink(query: String) = Uri.Builder()
        .scheme(APIConstant.SCHEME_API)
        .authority(APIConstant.AUTHORITY)
        .appendPath(APIConstant.API_CONTENT)
        .appendPath(APIConstant.API_SEARCH)
        .appendQueryParameter(ParameterConstant.SEARCH_PARAM , query)
        .toString()
}
