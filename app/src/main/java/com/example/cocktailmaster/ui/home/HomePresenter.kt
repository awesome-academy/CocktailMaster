package com.example.cocktailmaster.ui.home

import com.example.cocktailmaster.data.model.Drink
import com.example.cocktailmaster.data.repository.RandomDrinksRemoteRepository
import com.example.cocktailmaster.data.repository.SearchDrinkRemoteRepository
import com.example.cocktailmaster.data.source.remote.utils.RequestAPICallback
import com.example.cocktailmaster.utils.AlphabetsConstant

class HomePresenter(
    private val view: HomeContract.View,
    private val drinksRepo: RandomDrinksRemoteRepository,
    private val searchRepo: SearchDrinkRemoteRepository
) : HomeContract.Presenter {

    override fun getRandomDrinks() {
        view.showLoading()
        drinksRepo.getRandomDrinks(object : RequestAPICallback<List<Drink>> {
            override fun onSuccess(data: List<Drink>) {
                view.hideLoading()
                view.showRandomDrinks(data)
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
        searchRepo.searchDrinks(query , object : RequestAPICallback<List<Drink>>{
            override fun onSuccess(data: List<Drink>) {
                view.showSearchDrinks(data)
            }

            override fun onFailed() {
                view.showError()
            }
        })
    }

    override fun excute() {
        getRandomDrinks()
        getAllAlphabets()
    }
}
