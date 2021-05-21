package com.example.cocktailmaster.ui.home

import com.example.cocktailmaster.base.BasePresenter
import com.example.cocktailmaster.base.BaseView
import com.example.cocktailmaster.data.model.Category
import com.example.cocktailmaster.data.model.Drink
import com.example.cocktailmaster.data.model.Ingredient

interface HomeContract {
    interface Presenter: BasePresenter {
        fun getRandomDrinks()
        fun getCategories()
        fun getIngredients()
    }

    interface View: BaseView {
        fun loadRandomDrinks(drinks: List<Drink>)
        fun loadCategories(categories: List<Category>)
        fun loadIngredients(ingredients: List<Ingredient>)
    }
}
