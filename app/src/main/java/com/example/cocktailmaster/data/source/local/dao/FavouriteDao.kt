package com.example.cocktailmaster.data.source.local.dao

import com.example.cocktailmaster.data.model.Drink

interface FavouriteDao {
    fun insertDrink(drink: Drink)
    fun getAllFavouriteDrinks(): List<Drink>
    fun isFavourite(id: Int): Boolean
    fun removeFavouriteDrink(id: Int)
}
