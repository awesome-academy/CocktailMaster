package com.example.cocktailmaster.ui

import android.content.Context
import com.example.cocktailmaster.R
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

object CategoryUtils {
    const val CATEGORY_MILK_SHAKE = "Milk / Float / Shake"
    const val CATEGORY_OTHER = "Other/Unknown"
    const val CATEGORY_COFFEE_TEA = "Coffee / Tea"
    const val CATEGORY_HOMEMADE = "Homemade Liqueur"
    const val CATEGORY_PARTY_DRINK = "Punch / Party Drink"
    const val CATEGORY_BEER = "Beer"
    const val CATEGORY_SOFT_DRINK = "Soft Drink / Soda"
    const val CATEGORY_ORDINARY = "Ordinary Drinks"
    const val CATEGORY_COCOA = "Cocoa"
    const val CATEGORY_COCKTAIL = "Cocktail"
    const val CATEGORY_SHOT = "Shot"

    fun getCategoryMap(category: String, context: Context) = when (category) {
        CATEGORY_COCKTAIL -> R.drawable.img_cocktail
        CATEGORY_ORDINARY -> R.drawable.img_original_drink
        CATEGORY_COCOA -> R.drawable.img_cocoa
        CATEGORY_COFFEE_TEA -> R.drawable.img_coffee_tea
        CATEGORY_SOFT_DRINK -> R.drawable.img_soda
        CATEGORY_PARTY_DRINK -> R.drawable.img_party_drink
        CATEGORY_MILK_SHAKE -> R.drawable.img_milk_shake
        CATEGORY_HOMEMADE -> R.drawable.img_home_made
        CATEGORY_BEER -> R.drawable.img_beer
        CATEGORY_OTHER -> R.drawable.img_other_drink
        CATEGORY_SHOT -> R.drawable.img_shot

        else -> R.drawable.image_error
    }

}
