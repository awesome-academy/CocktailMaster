package com.example.cocktailmaster.ui.home

import com.example.cocktailmaster.data.model.Drink
import com.example.cocktailmaster.data.repository.DrinksRemoteRepository
import com.example.cocktailmaster.data.source.remote.utils.RequestAPICallback
import com.example.cocktailmaster.utils.AlphabetsConstant

class HomePresenter(
    private val view: HomeContract.View,
    private val drinksRepo: DrinksRemoteRepository
) : HomeContract.Presenter {

    override fun getRandomDrinks() {
        view.showLoading()
        drinksRepo.getRandomDrinks(object : RequestAPICallback<Drink> {
            override fun onSuccess(data: Drink) {
                view.hideLoading()
                view.showRandomDrink(data)
            }

            override fun onFailed() {
                view.showError()
            }
        })
    }

    override fun getAllAlphabets() {
        view.showAlphabets(AlphabetsConstant.ALPHABETS.toList())
    }

    override fun getSearchDrinks(query: String) {
        drinksRepo.searchDrinks(query, object : RequestAPICallback<List<Drink>> {
            override fun onSuccess(data: List<Drink>) {
                view.showSearchDrinks(data)
            }

            override fun onFailed() {
                view.showError()
            }
        })
    }

    override fun execute() {
        getRandomDrinks()
        getAllAlphabets()
    }
}
