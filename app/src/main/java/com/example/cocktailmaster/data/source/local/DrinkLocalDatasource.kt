package com.example.cocktailmaster.data.source.local

import com.example.cocktailmaster.data.model.Drink
import com.example.cocktailmaster.data.source.DrinksDataSource
import com.example.cocktailmaster.data.source.local.dao.FavouriteDao
import com.example.cocktailmaster.data.source.local.utils.LocalAsynctask
import com.example.cocktailmaster.data.source.local.utils.OnLocalDataCallback

class DrinkLocalDatasource private constructor(
    private val dao: FavouriteDao
) : DrinksDataSource.Local {

    override fun insertDrink(drink: Drink, callback: OnLocalDataCallback<Unit>) {
        LocalAsynctask<Drink, Unit>(callback) {
            dao.insertDrink(drink)
        }
    }

    override fun getAllFavouriteDrinks(callback: OnLocalDataCallback<List<Drink>>) {
        LocalAsynctask<Unit, List<Drink>>(callback) {
            dao.getAllFavouriteDrinks()
        }
    }

    override fun isFavourite(id: Int, callback: OnLocalDataCallback<Boolean>) {
        LocalAsynctask<Int, Boolean>(callback) {
            dao.isFavourite(id)
        }
    }

    override fun removeFavouriteDrink(id: Int, callback: OnLocalDataCallback<Unit>) {
        LocalAsynctask<Int, Unit>(callback) {
            dao.removeFavouriteDrink(id)
        }
    }

    companion object {
        private var instance: DrinkLocalDatasource? = null

        fun getInstance(favoriteDao: FavouriteDao) =
            instance ?: DrinkLocalDatasource(favoriteDao).also { instance = it }
    }
}
