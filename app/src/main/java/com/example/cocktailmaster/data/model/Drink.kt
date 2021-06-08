package com.example.cocktailmaster.data.model

import android.content.ContentValues
import android.database.Cursor
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Drink(
    val id: Int,
    val name: String,
    val category: String,
    val alcoholic: String,
    val glass: String,
    val instruction: String,
    val thumb: String,
    val ingredients: List<String>,
    val measure: List<String>
) : Parcelable {

    var isFavourite = false

    constructor(cursor: Cursor) : this(
        cursor.getString(cursor.getColumnIndex(FAVOURITE_ID)).toInt(),
        cursor.getString(cursor.getColumnIndex(FAVOURITE_NAME)),
        cursor.getString(cursor.getColumnIndex(FAVOURITE_CATEGORY)),
        cursor.getString(cursor.getColumnIndex(FAVOURITE_ALCOHOLIC)),
        cursor.getString(cursor.getColumnIndex(FAVOURITE_GLASS)),
        cursor.getString(cursor.getColumnIndex(FAVOURITE_INSTRUCTION)),
        cursor.getString(cursor.getColumnIndex(FAVOURITE_THUMB)),
        cursor.getString(cursor.getColumnIndex(FAVOURITE_INGREDIENTS)).split(";"),
        cursor.getString(cursor.getColumnIndex(FAVOURITE_MEASURE)).split(";")
    )

    fun getContentValue(): ContentValues {
        val ingredientArray = StringBuilder()
        ingredients.apply {
            forEachIndexed { index, s ->
                ingredientArray.append(if (index < size - 1) "$s;" else s)
            }
        }
        val measureArray = StringBuilder()
        measure.apply {
            forEachIndexed { index, s ->
                measureArray.append(if (index < size - 1) "$s;" else s)
            }
        }
        return ContentValues().apply {
            put(FAVOURITE_ID, id)
            put(FAVOURITE_NAME, name)
            put(FAVOURITE_CATEGORY, category)
            put(FAVOURITE_ALCOHOLIC, alcoholic)
            put(FAVOURITE_GLASS, glass)
            put(FAVOURITE_INSTRUCTION, instruction)
            put(FAVOURITE_THUMB, thumb)
            put(FAVOURITE_INGREDIENTS, ingredientArray.toString())
            put(FAVOURITE_MEASURE, measureArray.toString())
        }
    }

    companion object {
        const val FAVOURITE_TABLE_NAME = "favourite"
        const val FAVOURITE_ID = "id"
        const val FAVOURITE_NAME = "name"
        const val FAVOURITE_CATEGORY = "category"
        const val FAVOURITE_ALCOHOLIC = "alcoholic"
        const val FAVOURITE_GLASS = "glass"
        const val FAVOURITE_INSTRUCTION = "instruction"
        const val FAVOURITE_THUMB = "thumb"
        const val FAVOURITE_INGREDIENTS = "ingredients"
        const val FAVOURITE_MEASURE = "measure"
    }
}
