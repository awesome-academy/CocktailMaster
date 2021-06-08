package com.example.cocktailmaster.ui.detaildrink

import com.example.cocktailmaster.data.model.Drink
import com.example.cocktailmaster.data.repository.DrinksRepository
import com.example.cocktailmaster.data.source.local.utils.OnLocalDataCallback
import com.example.cocktailmaster.data.source.remote.utils.RequestAPICallback

class DetailDrinkPresenter(
    private val view: DetailDrinkContract.View,
    private val repository: DrinksRepository
) : DetailDrinkContract.Presenter {

    override fun loadDetailDrink(id: Int) {
        view.showLoading()
        repository.getDrinkById(id, object : RequestAPICallback<Drink> {
            override fun onSuccess(data: Drink) {
                view.hideLoading()
                view.showDetailDrink(data)
            }

            override fun onFailed() {
                view.hideLoading()
                view.showError()
            }
        })
    }

    override fun isFavourite(id: Int) {
        repository.isFavourite(id, object : OnLocalDataCallback<Boolean> {
            override fun onSuccess(data: Boolean) {
                view.isFavourite(data)
            }

            override fun onFailed() {
                view.showErrorLocalDb()
            }
        })
    }

    override fun removeFavourite(id: Int) {
        view.showLoading()
        repository.removeFavouriteDrink(id, object : OnLocalDataCallback<Unit> {
            override fun onSuccess(data: Unit) {
                view.hideLoading()
                isFavourite(id)
            }

            override fun onFailed() {
                view.hideLoading()
                view.showErrorLocalDb()
            }
        })
    }

    override fun insertFavourite(drink: Drink) {
        view.showLoading()
        repository.insertDrink(drink, object : OnLocalDataCallback<Unit> {
            override fun onSuccess(data: Unit) {
                view.hideLoading()
                isFavourite(drink.id)
            }

            override fun onFailed() {
                view.hideLoading()
                view.showErrorLocalDb()
            }
        })
    }

    override fun execute() {
    }
}
