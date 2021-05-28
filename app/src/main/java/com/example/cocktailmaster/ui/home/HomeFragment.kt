package com.example.cocktailmaster.ui.home

import android.view.View
import androidx.appcompat.widget.SearchView
import com.example.cocktailmaster.R
import com.example.cocktailmaster.base.BaseFragment
import com.example.cocktailmaster.data.model.Drink
import com.example.cocktailmaster.databinding.FragmentHomeBinding
import com.example.cocktailmaster.ui.RepositoryUtils
import com.example.cocktailmaster.ui.detaildrink.DetailDrinkFragment
import com.example.cocktailmaster.ui.home.adapter.AlphabetAdapter
import com.example.cocktailmaster.ui.home.adapter.DrinksSearchAdapter
import com.example.cocktailmaster.ui.home.adapter.RandomDrinksAdapter
import com.example.cocktailmaster.ui.replaceFragment
import kotlinx.android.synthetic.main.alphabets_include.*

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
        listOf(binding.imageSearch, binding.imageFavourite).forEach {
            it.setOnClickListener(this)
        }
        binding.apply {
            recyclerDrinks.adapter = drinkAdapter
            recyclerAlphabets.adapter = alphabetAdapter
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
        binding.textRandomDrinks.visibility = View.VISIBLE
        this.drinks.apply {
            add(drink)
            drinkAdapter.setDrinks(this)
        }
        binding.cardDrinks.visibility = View.VISIBLE
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
        binding.progressDrinkLoading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.progressDrinkLoading.visibility = View.GONE
    }

    override fun onClick(v: View) {
        if (v.id == R.id.imageSearch) {
            binding.searchDrinks.visibility = View.VISIBLE
        }
    }

    override fun onClose(): Boolean {
        binding.apply {
            searchDrinks.visibility = View.GONE
            recyclerDrinksSearch.visibility = View.GONE
            cardDrinks.visibility = View.VISIBLE
        }

        return false
    }

    override fun onQueryTextSubmit(query: String?) = false

    override fun onQueryTextChange(newText: String?): Boolean {
        binding.apply {
            recyclerDrinksSearch.visibility = View.VISIBLE
            cardDrinks.visibility = View.GONE
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
