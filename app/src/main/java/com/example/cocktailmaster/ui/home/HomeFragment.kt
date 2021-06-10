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
import com.example.cocktailmaster.ui.ingredient.IngredientFragment
import com.example.cocktailmaster.ui.listdrink.ListDrinkFragment
import com.example.cocktailmaster.ui.replaceFragment
import com.example.cocktailmaster.ui.show
import com.example.cocktailmaster.utils.ModelConstant
import kotlinx.android.synthetic.main.alphabets_include.*
import kotlinx.android.synthetic.main.categories_include.*
import kotlinx.android.synthetic.main.ingredient_include.*

class HomeFragment :
    BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate),
    HomeContract.View,
    View.OnClickListener,
    SearchView.OnCloseListener,
    SearchView.OnQueryTextListener {

    private var presenter: HomePresenter? = null
    private val drinkAdapter = RandomDrinksAdapter(::onClickDrinkItem)
    private val alphabetAdapter = AlphabetAdapter(::onClickAlphabetItem)
    private val drinkSearchAdapter = DrinksSearchAdapter(::loadDrinkSearch)
    private val drinks = mutableListOf<Drink>()
    private val searchDrinks = mutableListOf<Drink>()

    override fun initViews() {
        binding?.apply {
            listOf(
                imageSearch,
                imageFavourite,
                textSeeAllCategory,
                textSeeAllIngredient,
                imageCocktailDrink,
                imageCocoaDrink,
                imageOriginalDrink,
                linearGin,
                linearRum,
                linearTequila,
                linearVodka
            ).forEach {
                it.setOnClickListener(this@HomeFragment)
            }
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
            RepositoryUtils.getDrinkRepo(context)
        )
        presenter?.execute()
    }

    override fun showRandomDrink(drink: Drink) {
        binding?.textRandomDrinks?.show()
        this.drinks.apply {
            add(drink)
            drinkAdapter.setDrinks(this)
        }
        binding?.cardDrinks?.show()
    }

    override fun showAlphabets(alphabets: List<Char>) {
        alphabetAdapter.setAlphabets(alphabets)
    }

    override fun showSearchDrinks(searchedDrinks: List<Drink>) {
        this.searchDrinks.apply {
            clear()
            addAll(searchedDrinks)
            drinkSearchAdapter.setDrinks(this)
        }
    }

    override fun showError() {
    }

    override fun showLoading() {
        binding?.progressDrinkLoading?.show()
    }

    override fun hideLoading() {
        binding?.progressDrinkLoading?.hide()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.imageSearch -> binding?.searchDrinks?.show()

            R.id.textSeeAllCategory -> onClickSeeAllCategory()

            R.id.textSeeAllIngredient -> onClickSeeAllIngredients()

            R.id.imageCocktailDrink -> onClickCocktailCategory()

            R.id.imageCocoaDrink -> onClickCocoaCategory()

            R.id.imageOriginalDrink -> onClickOrdinaryCategory()

            R.id.linearGin -> onClickGinIngredient()

            R.id.linearRum -> onClickRumIngredient()

            R.id.linearTequila -> onClickTequilaIngredient()

            R.id.linearVodka -> onClickVodkaIngredient()

            R.id.imageFavourite -> onClickFavourite()
        }
    }

    override fun onClose(): Boolean {
        binding?.apply {
            searchDrinks.hide()
            recyclerDrinksSearch.hide()
            cardDrinks.show()
        }

        return false
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let { loadDrinkSearch(it) }
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        binding?.apply {
            recyclerDrinksSearch.show()
            cardDrinks.hide()
        }
        if (!newText.isNullOrEmpty()) {
            presenter?.getSearchDrinks(newText.toString())
        }
        return false
    }

    override fun onPause() {
        super.onPause()
        binding?.apply {
            searchDrinks.apply {
                setQuery("", false)
                clearFocus()
            }
            recyclerDrinksSearch.hide()
        }
        searchDrinks.clear()
        drinkSearchAdapter.setDrinks(searchDrinks)
    }

    private fun onClickSeeAllCategory() =
        fragmentManager?.let { replaceFragment(it, CategoryFragment()) }

    private fun onClickSeeAllIngredients() =
        fragmentManager?.let { replaceFragment(it, IngredientFragment()) }

    private fun onClickCocktailCategory() {
        loadListDrinkFragment(
            ModelConstant.CATEGORY,
            binding?.includeCategories?.textCocktailDrink?.text.toString()
        )
    }

    private fun onClickCocoaCategory() {
        loadListDrinkFragment(
            ModelConstant.CATEGORY,
            binding?.includeCategories?.textCocoaDrink?.text.toString()
        )
    }

    private fun onClickOrdinaryCategory() {
        loadListDrinkFragment(
            ModelConstant.CATEGORY,
            binding?.includeCategories?.textOriginalDrink?.text.toString()
        )
    }

    private fun onClickGinIngredient() {
        loadListDrinkFragment(
            ModelConstant.INGREDIENT,
            binding?.includeIngredients?.textGin?.text.toString()
        )
    }

    private fun onClickRumIngredient() {
        loadListDrinkFragment(
            ModelConstant.INGREDIENT,
            binding?.includeIngredients?.textRum?.text.toString()
        )
    }

    private fun onClickTequilaIngredient() {
        loadListDrinkFragment(
            ModelConstant.INGREDIENT,
            binding?.includeIngredients?.textTequila?.text.toString()
        )
    }

    private fun onClickVodkaIngredient() {
        loadListDrinkFragment(
            ModelConstant.INGREDIENT,
            binding?.includeIngredients?.textVodka?.text.toString()
        )
    }

    private fun onClickFavourite() {
        loadListDrinkFragment(
            ModelConstant.FAVOURITE,
            getString(R.string.text_favourite)
        )
    }

    private fun onClickDrinkItem(drink: Drink) {
        fragmentManager?.let {
            replaceFragment(
                it, DetailDrinkFragment.getInstance(drink, 0)
            )
        }
    }

    private fun onClickAlphabetItem(alphabet: String) {
        loadListDrinkFragment(ModelConstant.FIRST_LETTER, alphabet)
    }

    private fun loadListDrinkFragment(filterType: String, filterName: String) {
        fragmentManager?.let {
            replaceFragment(
                it, ListDrinkFragment.getInstance(filterType, filterName)
            )
        }
    }

    private fun loadDrinkSearch(query: String) {
        loadListDrinkFragment(ModelConstant.DRINK_NAME, query)
    }
}
