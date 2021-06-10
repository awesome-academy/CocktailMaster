package com.example.cocktailmaster.ui.category

import android.view.View
import com.example.cocktailmaster.R
import com.example.cocktailmaster.base.BaseFragment
import com.example.cocktailmaster.data.model.Category
import com.example.cocktailmaster.databinding.FragmentCategoryLayoutBinding
import com.example.cocktailmaster.ui.*
import com.example.cocktailmaster.ui.listdrink.ListDrinkFragment
import com.example.cocktailmaster.utils.AsynctaskState
import com.example.cocktailmaster.utils.ModelConstant

class CategoryFragment :
    BaseFragment<FragmentCategoryLayoutBinding>(FragmentCategoryLayoutBinding::inflate),
    CategoryContract.View,
    View.OnClickListener {

    private var presenter: CategoryPresenter? = null
    private val categories = mutableListOf<Category>()
    private val categoryAdapter = CategoryAdapter(::onClickCategoryItem)

    override fun initViews() {
        binding?.apply {
            recyclerCategories.adapter = categoryAdapter
            imageBack.setOnClickListener(this@CategoryFragment)
        }
    }

    override fun initData() {
        categories.clear()
        presenter = CategoryPresenter(this, RepositoryUtils.getCategoriesRepo())
        presenter?.execute()
    }

    override fun showCategories(categories: List<Category>) {
        this.categories.addAll(categories)
        categoryAdapter.setCategories(this.categories)
        binding?.apply {
            imageEmpty.hide()
            textEmpty.hide()
        }
    }

    override fun showError() {
        binding?.apply {
            textEmpty.show()
            imageEmpty.show()
        }
    }

    override fun showLoading() {
        binding?.progressCategory?.show()
    }

    override fun hideLoading() {
        binding?.progressCategory?.hide()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.imageBack) {
            if (!AsynctaskState.isFinished) {
                AsynctaskState.isCancelled = true
            }
            AsynctaskState.isFinished = false
            fragmentManager?.let { popFragment(it, this) }
        }
    }

    private fun onClickCategoryItem(category: Category) {
        fragmentManager?.let {
            replaceFragment(
                it,
                ListDrinkFragment.getInstance(ModelConstant.CATEGORY, category.name)
            )
        }
    }
}
