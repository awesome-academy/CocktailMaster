package com.example.cocktailmaster.ui.home

import android.view.View
import com.example.cocktailmaster.base.BaseFragment
import com.example.cocktailmaster.data.model.Drink
import com.example.cocktailmaster.databinding.FragmentHomeBinding
import com.example.cocktailmaster.ui.RepositoryUtils
import com.example.cocktailmaster.ui.home.adapter.AlphabetAdapter
import com.example.cocktailmaster.ui.home.adapter.RandomDrinksAdapter

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate),
    HomeContract.View {

    private var presenter: HomePresenter? = null
    private val drinkAdapter = RandomDrinksAdapter()
    private val alphabetAdapter = AlphabetAdapter()
    private val drinks = mutableListOf<Drink>()

    override fun initViews() {
        binding.recyclerDrinks.adapter = drinkAdapter
        binding.includeAlphabets.recyclerAlphabets.adapter = alphabetAdapter
    }

    override fun initData() {
        val randomDrinksRepo = RepositoryUtils.getRandomDrinksRepo()
        presenter = HomePresenter(this, randomDrinksRepo)
        presenter?.excute()
    }

    override fun showRandomDrinks(drinks: List<Drink>) {
        binding.textRandomDrinks.visibility = View.VISIBLE
        this.drinks.addAll(drinks)
        drinkAdapter.setDrinks(this.drinks)
    }

    override fun showAlphabets(alphabets: List<Char>) {
        alphabetAdapter.setAlphabets(alphabets)
    }

    override fun showError() {
    }

    override fun showLoading() {
        binding.progressDrinkLoading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.progressDrinkLoading.visibility = View.GONE
    }
}
