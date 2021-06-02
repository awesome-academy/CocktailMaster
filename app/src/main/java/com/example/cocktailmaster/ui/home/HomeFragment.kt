package com.example.cocktailmaster.ui.home

import android.view.View
import androidx.appcompat.widget.SearchView
import com.example.cocktailmaster.R
import com.example.cocktailmaster.base.BaseFragment
import com.example.cocktailmaster.data.model.Drink
import com.example.cocktailmaster.databinding.FragmentHomeBinding
import com.example.cocktailmaster.ui.RepositoryUtils
import com.example.cocktailmaster.ui.category.CategoryFragment
import com.example.cocktailmaster.ui.detaildrink.DetailDrinkFragment
import com.example.cocktailmaster.ui.hide
import com.example.cocktailmaster.ui.home.adapter.AlphabetAdapter
import com.example.cocktailmaster.ui.home.adapter.DrinksSearchAdapter
import com.example.cocktailmaster.ui.home.adapter.RandomDrinksAdapter
import com.example.cocktailmaster.ui.replaceFragment
import com.example.cocktailmaster.ui.show

class HomeFragment :
    BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate),
    HomeContract.View,
    View.OnClickListener,
    SearchView.OnCloseListener,
    SearchView.OnQueryTextListener {

    private var presenter: HomePresenter? = null
    private val drinkAdapter = RandomDrinksAdapter(::onClickDrinkItem)
    private val alphabetAdapter = AlphabetAdapter()
    private val drinkSearchAdapter = DrinksSearchAdapter()
    private val drinks = mutableListOf<Drink>()

    override fun initViews() {
        binding.apply {
            listOf(imageSearch, imageFavourite, includeCategories.textSeeAllCategory).forEach {
                it.setOnClickListener(this@HomeFragment)
            }
            recyclerDrinks.adapter = drinkAdapter
            includeAlphabets.recyclerAlphabets.adapter = alphabetAdapter
            recyclerDrinksSearch.adapter = drinkSearchAdapter

            searchDrinks.apply {
                setOnCloseListener(this@HomeFragment)
                setOnQueryTextListener(this@HomeFragment)
            }
        }

    }

    override fun initData() {
        drinks.clear()
        presenter = HomePresenter(
            this,
            RepositoryUtils.getDrinkRepo()
        )
        presenter?.excute()
    }

    override fun showRandomDrink(drink: Drink) {
        binding.textRandomDrinks.show()
        this.drinks.apply {
            add(drink)
            drinkAdapter.setDrinks(this)
        }
        binding.cardDrinks.show()
    }

    override fun showAlphabets(alphabets: List<Char>) {
        alphabetAdapter.setAlphabets(alphabets)
    }

    override fun showSearchDrinks(searchedDrinks: List<Drink>) {
        drinkSearchAdapter.setDrinks(searchedDrinks)
    }

    override fun showError() {
    }

    override fun showLoading() {
        binding.progressDrinkLoading.show()
    }

    override fun hideLoading() {
        binding.progressDrinkLoading.hide()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.imageSearch -> binding.searchDrinks.show()

            R.id.textSeeAllCategory -> fragmentManager?.let {
                replaceFragment(
                    it,
                    CategoryFragment()
                )
            }
        }
    }

    override fun onClose(): Boolean {
        binding.apply {
            searchDrinks.hide()
            recyclerDrinksSearch.hide()
            cardDrinks.show()
        }

        return false
    }

    override fun onQueryTextSubmit(query: String?) = false

    override fun onQueryTextChange(newText: String?): Boolean {
        binding.apply {
            recyclerDrinksSearch.show()
            cardDrinks.hide()
        }
        presenter?.getSearchDrinks(newText.toString())
        return false
    }

    private fun onClickDrinkItem(drink: Drink) {
        fragmentManager?.let {
            replaceFragment(
                it, DetailDrinkFragment.getInstance(drink)
            )
        }
    }
}
