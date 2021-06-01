package com.example.cocktailmaster.ui.category

import com.example.cocktailmaster.base.BasePresenter
import com.example.cocktailmaster.base.BaseView
import com.example.cocktailmaster.data.model.Category

interface CategoryContract {

    interface Presenter: BasePresenter{
        fun getCategories()
    }

    interface View: BaseView {
        fun showCategories(categories: List<Category>)
    }
}
