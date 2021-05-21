package com.example.cocktailmaster.data.repository

import com.example.cocktailmaster.data.source.CategoryDataSource
import com.example.cocktailmaster.data.model.Category
import com.example.cocktailmaster.data.source.remote.utils.RequestAPICallback

class CategoryRemoteRepository private constructor(
    private val remote: CategoryDataSource
) : CategoryDataSource {
    override fun getCategories(callback: RequestAPICallback<List<Category>>) {
        remote.getCategories(callback)
    }

    companion object {
        private var instance: CategoryRemoteRepository? = null

        fun getInstance(remote: CategoryDataSource) =
            instance ?: CategoryRemoteRepository(remote).also { instance = it }
    }
}
