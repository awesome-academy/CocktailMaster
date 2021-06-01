package com.example.cocktailmaster.data.source.remote.utils

import com.example.cocktailmaster.R
import com.example.cocktailmaster.data.model.Category
import com.example.cocktailmaster.data.model.Drink
import com.example.cocktailmaster.data.model.Ingredient
import com.example.cocktailmaster.utils.ModelConstant
import org.json.JSONArray
import org.json.JSONException

const val nullStr = "null"
inline fun <reified T> parseToJsonArray(model: String, jsonArray: JSONArray) = jsonArray.run {
    List(length()) {
        when (model) {
            ModelConstant.DRINK -> {
                val jsonObject = getJSONObject(it)
                val id = jsonObject.optString(DrinkConstant.ID).toInt()
                val name = jsonObject.optString(DrinkConstant.NAME)
                val category = jsonObject.optString(DrinkConstant.CATEGORY)
                val alcoholic = jsonObject.optString(DrinkConstant.ALCOHOLIC)
                val glass = jsonObject.optString(DrinkConstant.GLASS)
                val instruction = jsonObject.optString(DrinkConstant.INSTRUCTION)
                val thumb = jsonObject.optString(DrinkConstant.THUMB)
                val ingredients: MutableList<String> = ArrayList()
                val measurements: MutableList<String> = ArrayList()
                DrinkConstant.INGREDIENTS.forEach {
                    val ingredient = jsonObject.optString(it)
                    if (!ingredient.equals(nullStr) && !ingredient.isNullOrEmpty()) {
                        ingredients.add(jsonObject.optString(it))
                    }
                }
                DrinkConstant.MEASUREMENTS.forEach {
                    val measurement = jsonObject.optString(it)
                    if (!measurement.equals(nullStr) && !measurement.isNullOrEmpty()) {
                        measurements.add(jsonObject.optString(it))
                    }
                }
                Drink(
                    id, name, category, alcoholic, glass,
                    instruction, thumb, ingredients, measurements
                ) as T
            }

            ModelConstant.CATEGORY -> Category(getJSONObject(it)) as T

            ModelConstant.INGREDIENT -> Ingredient(getJSONObject(it)) as T

            else -> throw JSONException(R.string.exception_parse_json.toString())
        }
    }
}
