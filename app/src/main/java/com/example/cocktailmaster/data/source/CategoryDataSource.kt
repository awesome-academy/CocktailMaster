package com.example.cocktailmaster.data.source

import com.example.cocktailmaster.data.model.Category
import com.example.cocktailmaster.data.source.remote.utils.RequestAPICallback

interface CategoryDataSource {
    fun getCategories(callback: RequestAPICallback<List<Category>>)
}
