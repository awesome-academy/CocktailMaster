package com.example.cocktailmaster.ui.listdrink

import com.example.cocktailmaster.data.model.Drink
import com.example.cocktailmaster.data.repository.DrinksRemoteRepository
import com.example.cocktailmaster.data.source.remote.utils.RequestAPICallback

class ListDrinkPresenter(
    private val view: ListDrinkContract.View,
    private val drinkRepo: DrinksRemoteRepository,
) : ListDrinkContract.Presenter {

    override fun getListDrinkByCategory(category: String) {
        view.showLoading()
        drinkRepo.filterDrinkByCategory(category, object : RequestAPICallback<List<Drink>> {
            override fun onSuccess(data: List<Drink>) {
                view.hideLoading()
                view.showDrinks(data)
            }

            override fun onFailed() {
                view.hideLoading()
                view.showError()
            }
        })
    }

    override fun getListDrinkByIngredient(ingredient: String) {
        view.showLoading()
        drinkRepo.filterDrinkByIngredient(ingredient, object : RequestAPICallback<List<Drink>> {
            override fun onSuccess(data: List<Drink>) {
                view.hideLoading()
                view.showDrinks(data)
            }

            override fun onFailed() {
                view.hideLoading()
                view.showError()
            }
        })
    }

    override fun getListDrinkByFirstLetter(letter: String) {
        view.showLoading()
        drinkRepo.filterDrinkByFirstLetter(letter, object : RequestAPICallback<List<Drink>> {
            override fun onSuccess(data: List<Drink>) {
                view.hideLoading()
                view.showDrinks(data)
            }

            override fun onFailed() {
                view.hideLoading()
                view.showError()
            }
        })
    }

    override fun execute() {
    }
}
