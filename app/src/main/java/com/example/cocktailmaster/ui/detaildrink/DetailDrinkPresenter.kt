package com.example.cocktailmaster.ui.detaildrink

import com.example.cocktailmaster.data.model.Drink
import com.example.cocktailmaster.data.repository.DrinksRemoteRepository
import com.example.cocktailmaster.data.source.remote.utils.RequestAPICallback

class DetailDrinkPresenter(
    private val view: DetailDrinkContract.View,
    private val repository: DrinksRemoteRepository
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

    override fun execute() {
    }
}
