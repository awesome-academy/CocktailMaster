package com.example.cocktailmaster.data.source.local.dao

import com.example.cocktailmaster.data.model.Drink
import com.example.cocktailmaster.data.model.Drink.Companion.FAVOURITE_ID
import com.example.cocktailmaster.data.model.Drink.Companion.FAVOURITE_TABLE_NAME
import com.example.cocktailmaster.data.source.local.db.AppDatabase

class FavouriteDaoImpl private constructor(
    appDatabase: AppDatabase
) : FavouriteDao {

    private val writeDb = appDatabase.writableDatabase
    private val readDb = appDatabase.readableDatabase

    override fun insertDrink(drink: Drink) {
        writeDb.insert(FAVOURITE_TABLE_NAME, null, drink.getContentValue())
    }

    override fun getAllFavouriteDrinks(): List<Drink> {
        val drinks = mutableListOf<Drink>()
        val query = "SELECT * FROM $FAVOURITE_TABLE_NAME"
        val cursor = readDb.rawQuery(query, null)

        cursor.use {
            while (it.moveToNext()) {
                drinks.add(Drink(it))
            }
        }
        return drinks
    }

    override fun isFavourite(id: Int): Boolean {
        val query = "$FAVOURITE_ID = ?"
        val cursor = readDb.query(
            FAVOURITE_TABLE_NAME,
            null,
            query,
            arrayOf(id.toString()),
            null,
            null,
            null
        )
        return cursor.count > 0
    }

    override fun removeFavouriteDrink(id: Int) {
        writeDb.delete(FAVOURITE_TABLE_NAME, "${FAVOURITE_ID}=?", arrayOf(id.toString()))
    }

    companion object {
        private var instance: FavouriteDaoImpl? = null

        fun getInstance(database: AppDatabase): FavouriteDaoImpl =
            instance ?: FavouriteDaoImpl(database).also {
                instance = it
            }
    }
}
