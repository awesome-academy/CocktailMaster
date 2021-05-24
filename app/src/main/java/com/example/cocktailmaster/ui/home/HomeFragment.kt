package com.example.cocktailmaster.ui.home

import android.view.View
import com.example.cocktailmaster.base.BaseFragment
import com.example.cocktailmaster.data.model.Category
import com.example.cocktailmaster.data.model.Drink
import com.example.cocktailmaster.data.model.Ingredient
import com.example.cocktailmaster.databinding.FragmentHomeBinding
import com.example.cocktailmaster.ui.RepositoryUtils

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate),
    HomeContract.View {

    private var presenter: HomePresenter? = null
    private val drinkAdapter = RandomDrinksAdapter()

    override fun initViews() {
    }

    override fun initData() {
        val randomDrinksRepo = RepositoryUtils.getRandomDrinksRepo()
        val categoriesRepo = RepositoryUtils.getCategoriesRepo()
        val ingredientsRepo = RepositoryUtils.getIngredientsRepo()
        presenter = HomePresenter(this, randomDrinksRepo , categoriesRepo , ingredientsRepo)
        presenter?.excute()
    }

    override fun loadRandomDrinks(drinks: List<Drink>) {
        binding.textRandomDrinks.visibility = View.VISIBLE
        drinkAdapter.setDrinks(drinks)
        binding.recyclerDrinks.adapter = drinkAdapter
    }

    override fun loadCategories(categories: List<Category>) {
    }

    override fun loadIngredients(ingredients: List<Ingredient>) {
    }

    override fun showError() {
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }
}
