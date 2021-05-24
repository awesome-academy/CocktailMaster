package com.example.cocktailmaster.ui.home

import android.view.View
import com.example.cocktailmaster.base.BaseFragment
import com.example.cocktailmaster.data.model.Drink
import com.example.cocktailmaster.databinding.FragmentHomeBinding
import com.example.cocktailmaster.ui.RepositoryUtils

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate),
    HomeContract.View {

    private var presenter: HomePresenter? = null
    private val drinkAdapter = RandomDrinksAdapter()
    private val drinks = mutableListOf<Drink>()

    override fun initViews() {
        binding.recyclerDrinks.adapter = drinkAdapter
    }

    override fun initData() {
        val randomDrinksRepo = RepositoryUtils.getRandomDrinksRepo()
        presenter = HomePresenter(this, randomDrinksRepo)
        presenter?.excute()
    }

    override fun loadRandomDrinks(drinks: List<Drink>) {
        binding.textRandomDrinks.visibility = View.VISIBLE
        this.drinks.addAll(drinks)
        drinkAdapter.setDrinks(this.drinks)
    }

    override fun showError() {
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }
}
