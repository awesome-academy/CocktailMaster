package com.example.cocktailmaster.data.model

import com.example.cocktailmaster.data.source.remote.utils.DrinkConstant
import org.json.JSONObject

class Ingredient(val name: String) {

    constructor(jsonObject: JSONObject) : this(
        jsonObject.optString(DrinkConstant.INGREDIENT1)
    )
}
