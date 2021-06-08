package com.example.cocktailmaster.data.source.local.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.cocktailmaster.data.model.Drink.Companion.FAVOURITE_ALCOHOLIC
import com.example.cocktailmaster.data.model.Drink.Companion.FAVOURITE_CATEGORY
import com.example.cocktailmaster.data.model.Drink.Companion.FAVOURITE_GLASS
import com.example.cocktailmaster.data.model.Drink.Companion.FAVOURITE_ID
import com.example.cocktailmaster.data.model.Drink.Companion.FAVOURITE_INGREDIENTS
import com.example.cocktailmaster.data.model.Drink.Companion.FAVOURITE_INSTRUCTION
import com.example.cocktailmaster.data.model.Drink.Companion.FAVOURITE_MEASURE
import com.example.cocktailmaster.data.model.Drink.Companion.FAVOURITE_NAME
import com.example.cocktailmaster.data.model.Drink.Companion.FAVOURITE_TABLE_NAME
import com.example.cocktailmaster.data.model.Drink.Companion.FAVOURITE_THUMB

class AppDatabase private constructor(
    context: Context?,
    dbName: String,
    version: Int
) : SQLiteOpenHelper(context, dbName, null, version) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_FAVOURITE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DROP_FAVOURITE_TABLE)
        onCreate(db)
    }

    companion object {
        private const val DB_VERSION = 1
        private const val DB_NAME = "vietkfc.db"

        private val CREATE_FAVOURITE_TABLE = String.format(
            "CREATE TABLE %s (%s TEXT PRIMARY KEY,  %s TEXT, %s TEXT, %s TEXT, %s TEXT," +
                    " %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
            FAVOURITE_TABLE_NAME,
            FAVOURITE_ID,
            FAVOURITE_NAME,
            FAVOURITE_CATEGORY,
            FAVOURITE_ALCOHOLIC,
            FAVOURITE_GLASS,
            FAVOURITE_INSTRUCTION,
            FAVOURITE_THUMB,
            FAVOURITE_INGREDIENTS,
            FAVOURITE_MEASURE
        )

        private val DROP_FAVOURITE_TABLE =
            String.format("DROP TABLE IF EXISTS %s", FAVOURITE_TABLE_NAME)

        private val lock = Any()
        private var instance: AppDatabase? = null

        fun getInstance(context: Context?) = instance ?: synchronized(lock) {
            instance ?: AppDatabase(context, DB_NAME, DB_VERSION).also { instance = it }
        }
    }
}
