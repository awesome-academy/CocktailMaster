package com.example.cocktailmaster.data.model

data class Drink(
    private val id: Int,
    private val name: String,
    private val category: String,
    private val alcoholic: String,
    private val glass: String,
    private val instruction: String,
    private val thumb: String,
    private val ingredients: List<String>,
    private val measure: List<String>
)
