package com.example.cocktailmaster.ui.listdrink

import com.example.cocktailmaster.data.model.Drink
import com.example.cocktailmaster.data.repository.DrinksRepository
import com.example.cocktailmaster.data.source.local.utils.OnLocalDataCallback
import com.example.cocktailmaster.data.source.remote.utils.RequestAPICallback

class ListDrinkPresenter(
    private val view: ListDrinkContract.View,
    private val drinkRepo: DrinksRepository,
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

    override fun insertFavourite(drink: Drink) {
        view.showLoading()
        drinkRepo.insertDrink(drink , object : OnLocalDataCallback<Unit>{
            override fun onSuccess(data: Unit) {
                view.hideLoading()
            }

            override fun onFailed() {
                view.hideLoading()
                view.showError()
            }
        })
    }

    override fun getAllFavouriteDrinks() {
        view.showLoading()
        drinkRepo.getAllFavouriteDrinks(object: OnLocalDataCallback<List<Drink>>{
            override fun onSuccess(data: List<Drink>) {
                view.hideLoading()
                view.showAllFavouriteDrinks(data)
            }

            override fun onFailed() {
                view.hideLoading()
                view.showError()
            }
        })
    }

    override fun isFavourite(id: Int) {
        drinkRepo.isFavourite(id , object : OnLocalDataCallback<Boolean>{
            override fun onSuccess(data: Boolean) {
                view.isFavourite(data)
            }

            override fun onFailed() {
                view.showError()
            }
        })
    }

    override fun removeFavourite(id: Int) {
        view.showLoading()
        drinkRepo.removeFavouriteDrink(id , object : OnLocalDataCallback<Unit>{
            override fun onSuccess(data: Unit) {
                view.hideLoading()
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
