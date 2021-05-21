package com.example.cocktailmaster.data.model

import com.example.cocktailmaster.data.source.remote.utils.DrinkConstant
import org.json.JSONObject

class Category(private val name: String) {

    constructor(jsonObject: JSONObject) : this(
        jsonObject.optString(DrinkConstant.CATEGORY)
    )
}
