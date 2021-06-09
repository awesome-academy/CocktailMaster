package com.example.cocktailmaster.ui.detaildrink

import com.example.cocktailmaster.base.BasePresenter
import com.example.cocktailmaster.base.BaseView
import com.example.cocktailmaster.data.model.Drink

interface DetailDrinkContract {

    interface Presenter : BasePresenter {
        fun loadDetailDrink(id: Int)
        fun isFavourite(id: Int)
        fun removeFavourite(id: Int)
        fun insertFavourite(drink: Drink)
    }

    interface View : BaseView {
        fun showDetailDrink(drink: Drink)
        fun isFavourite(isFavourite: Boolean)
        fun showErrorLocalDb()
    }
}
