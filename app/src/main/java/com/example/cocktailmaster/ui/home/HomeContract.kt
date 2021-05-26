package com.example.cocktailmaster.ui.home

import com.example.cocktailmaster.base.BasePresenter
import com.example.cocktailmaster.base.BaseView
import com.example.cocktailmaster.data.model.Drink

interface HomeContract {
    interface Presenter: BasePresenter {
        fun getRandomDrinks()
        fun getAllAlphabets()
    }

    interface View: BaseView {
        fun showRandomDrinks(drinks: List<Drink>)
        fun showAlphabets(alphabets: List<Char>)
    }
}
