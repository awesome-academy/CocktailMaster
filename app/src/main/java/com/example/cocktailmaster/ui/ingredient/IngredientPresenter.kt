package com.example.cocktailmaster.ui.ingredient

import com.example.cocktailmaster.data.model.Ingredient
import com.example.cocktailmaster.data.repository.IngredientRemoteRepository
import com.example.cocktailmaster.data.source.remote.utils.RequestAPICallback

class IngredientPresenter(
    private val view: IngredientContract.View,
    private val repo: IngredientRemoteRepository
) : IngredientContract.Presenter {
    override fun getIngredients() {
        view.showLoading()
        repo.getIngredients(object : RequestAPICallback<List<Ingredient>> {
            override fun onSuccess(data: List<Ingredient>) {
                view.hideLoading()
                view.showIngredients(data)
            }

            override fun onFailed() {
                view.hideLoading()
                view.showError()
            }
        })
    }

    override fun excute() {
        getIngredients()
    }
}
