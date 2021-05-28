package com.example.cocktailmaster.data.model

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
): Parcelable
