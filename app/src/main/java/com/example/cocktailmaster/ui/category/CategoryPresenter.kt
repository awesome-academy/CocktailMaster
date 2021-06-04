package com.example.cocktailmaster.ui.category

import com.example.cocktailmaster.data.model.Category
import com.example.cocktailmaster.data.repository.CategoryRemoteRepository
import com.example.cocktailmaster.data.source.remote.utils.RequestAPICallback

class CategoryPresenter(
    private val categoryView: CategoryContract.View,
    private val categoryRepo: CategoryRemoteRepository,
) : CategoryContract.Presenter {

    override fun getCategories() {
        categoryView.showLoading()
        categoryRepo.getCategories(object : RequestAPICallback<List<Category>> {
            override fun onSuccess(data: List<Category>) {
                categoryView.showCategories(data)
                categoryView.hideLoading()
            }

            override fun onFailed() {
                categoryView.showError()
                categoryView.hideLoading()
            }
        })
    }

    override fun execute() {
        getCategories()
    }
}
