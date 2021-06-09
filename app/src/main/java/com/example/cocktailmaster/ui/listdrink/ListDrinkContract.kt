package com.example.cocktailmaster.ui.listdrink

import com.example.cocktailmaster.base.BasePresenter
import com.example.cocktailmaster.base.BaseView
import com.example.cocktailmaster.data.model.Drink

interface ListDrinkContract {

    interface Presenter : BasePresenter {
        fun getListDrinkByCategory(category: String)
        fun getListDrinkByIngredient(ingredient: String)
        fun getListDrinkByFirstLetter(letter: String)
        fun getDrinkByName(name: String)
        fun insertFavourite(drink: Drink)
        fun getAllFavouriteDrinks()
        fun isFavourite(id: Int, position: Int)
        fun removeFavourite(id: Int)
    }

    interface View : BaseView {
        fun showDrinks(drinks: List<Drink>)
        fun showAllFavouriteDrinks(drinks: List<Drink>)
        fun isFavourite(isFavourite: Boolean, position: Int)
    }
}
