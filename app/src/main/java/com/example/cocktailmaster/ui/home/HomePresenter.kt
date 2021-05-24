package com.example.cocktailmaster.ui.home

import com.example.cocktailmaster.data.model.Drink
import com.example.cocktailmaster.data.repository.RandomDrinksRemoteRepository
import com.example.cocktailmaster.data.source.remote.utils.RequestAPICallback

class HomePresenter(
    private val view: HomeContract.View,
    private val drinksRepo: RandomDrinksRemoteRepository,
) : HomeContract.Presenter {
    override fun getRandomDrinks() {
        view.showLoading()
        drinksRepo.getRandomDrinks(object : RequestAPICallback<List<Drink>>{
            override fun onSuccess(data: List<Drink>) {
                view.hideLoading()
                view.loadRandomDrinks(data)
            }

            override fun onFailed() {
                view.hideLoading()
                view.showError()
            }
        })
    }

    override fun excute() {
        getRandomDrinks()
    }
}
