package com.example.cocktailmaster.ui.listdrink

import com.example.cocktailmaster.base.BasePresenter
import com.example.cocktailmaster.base.BaseView
import com.example.cocktailmaster.data.model.Drink

interface ListDrinkContract {

    interface Presenter : BasePresenter {
        fun getListDrinkByCategory(category: String)
        fun getListDrinkByIngredient(ingredient: String)
        fun getListDrinkByFirstLetter(letter: String)
    }

    interface View : BaseView {
        fun showDrinksByCategory(drinks: List<Drink>)
        fun showDrinksByIngredient(drinks: List<Drink>)
        fun showDrinksByFirstLetter(drinks: List<Drink>)
    }
}
