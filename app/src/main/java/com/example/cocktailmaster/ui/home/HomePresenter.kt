package com.example.cocktailmaster.ui.home

import com.example.cocktailmaster.data.model.Category
import com.example.cocktailmaster.data.model.Drink
import com.example.cocktailmaster.data.model.Ingredient
import com.example.cocktailmaster.data.repository.CategoryRemoteRepository
import com.example.cocktailmaster.data.repository.IngredientRemoteRepository
import com.example.cocktailmaster.data.repository.RandomDrinksRemoteRepository
import com.example.cocktailmaster.data.source.remote.utils.RequestAPICallback

class HomePresenter(
    private val view: HomeContract.View,
    private val drinksRepo: RandomDrinksRemoteRepository,
    private val categoryRepo: CategoryRemoteRepository,
    private val ingredientRepo: IngredientRemoteRepository
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

    override fun getCategories() {
        view.showLoading()
        categoryRepo.getCategories(object : RequestAPICallback<List<Category>>{
            override fun onSuccess(data: List<Category>) {
                view.hideLoading()
                view.loadCategories(data)
            }

            override fun onFailed() {
                view.hideLoading()
                view.showError()
            }

        })
    }

    override fun getIngredients() {
        view.showLoading()
        ingredientRepo.getIngredients(object : RequestAPICallback<List<Ingredient>>{
            override fun onSuccess(data: List<Ingredient>) {
                view.hideLoading()
                view.loadIngredients(data)
            }

            override fun onFailed() {
                view.hideLoading()
                view.showError()
            }

        })
    }

    override fun excute() {
        getRandomDrinks()
        getCategories()
        getIngredients()
    }
}
