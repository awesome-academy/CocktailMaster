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

    override fun getDrinkByName(name: String) {
        view.showLoading()
        drinkRepo.getDrinkByName(name, object : RequestAPICallback<List<Drink>> {
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

    override fun getDrinkById(id: Int , position: Int) {
        drinkRepo.getDrinkById(id , object : RequestAPICallback<Drink> {
            override fun onSuccess(data: Drink) {
                view.showDrink(data , position)
            }

            override fun onFailed() {
                view.showError()
            }
        })
    }

    override fun insertFavourite(drink: Drink , position: Int) {
        view.showLoading()
        drinkRepo.insertDrink(drink, object : OnLocalDataCallback<Unit> {
            override fun onSuccess(data: Unit) {
                view.hideLoading()
                isFavourite(drink.id, position)
            }

            override fun onFailed() {
                view.hideLoading()
                view.showError()
            }
        })
    }

    override fun getAllFavouriteDrinks() {
        view.showLoading()
        drinkRepo.getAllFavouriteDrinks(object : OnLocalDataCallback<List<Drink>> {
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

    override fun isFavourite(id: Int, position: Int) {
        drinkRepo.isFavourite(id, object : OnLocalDataCallback<Boolean> {
            override fun onSuccess(data: Boolean) {
                view.isFavourite(data, position)
            }

            override fun onFailed() {
                view.showError()
            }
        })
    }

    override fun removeFavourite(id: Int , position: Int) {
        view.showLoading()
        drinkRepo.removeFavouriteDrink(id, object : OnLocalDataCallback<Unit> {
            override fun onSuccess(data: Unit) {
                view.hideLoading()
                isFavourite(id, position)
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
