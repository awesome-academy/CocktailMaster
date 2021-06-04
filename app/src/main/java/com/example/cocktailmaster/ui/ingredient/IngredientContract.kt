package com.example.cocktailmaster.ui.ingredient

import com.example.cocktailmaster.base.BasePresenter
import com.example.cocktailmaster.base.BaseView
import com.example.cocktailmaster.data.model.Ingredient

interface IngredientContract {

    interface Presenter : BasePresenter {
        fun getIngredients()
    }

    interface View : BaseView {
        fun showIngredients(ingredients: List<Ingredient>)
    }
}
