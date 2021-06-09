package com.example.cocktailmaster.ui.listdrink

import android.view.View
import androidx.core.os.bundleOf
import com.example.cocktailmaster.R
import com.example.cocktailmaster.base.BaseFragment
import com.example.cocktailmaster.data.model.Drink
import com.example.cocktailmaster.databinding.FragmentListDrinkBinding
import com.example.cocktailmaster.ui.*
import com.example.cocktailmaster.ui.detaildrink.DetailDrinkFragment
import com.example.cocktailmaster.utils.ModelConstant

class ListDrinkFragment :
    BaseFragment<FragmentListDrinkBinding>(FragmentListDrinkBinding::inflate),
    ListDrinkContract.View,
    View.OnClickListener {

    private var presenter: ListDrinkPresenter? = null
    private val drinks = mutableListOf<Drink>()
    private val adapter = ListDrinkAdapter(::onClickDrinkItem)
    private val filterType by lazy { arguments?.getString(BUNDLE_DRINKS_FILTER) }
    private val filterName by lazy { arguments?.getString(BUNDLE_FILTER_NAME) }

    override fun initViews() {
        binding.apply {
            imageBack.setOnClickListener(this@ListDrinkFragment)
            recyclerListDrink.adapter = adapter
            ModelConstant.apply {
                textListDrink.text = when (filterType) {
                    FIRST_LETTER -> {
                        resources.getString(
                            R.string.text_filter_first_letter,
                            filterName?.uppercase()
                        )
                    }
                    else -> filterName
                }
            }
        }
    }

    override fun initData() {
        presenter = ListDrinkPresenter(this, RepositoryUtils.getDrinkRepo(context))
        presenter?.apply {
            ModelConstant.apply {
                filterName.toString().apply {
                    when (filterType) {
                        CATEGORY -> getListDrinkByCategory(this)
                        INGREDIENT -> getListDrinkByIngredient(this)
                        FIRST_LETTER -> getListDrinkByFirstLetter(this)
                        DRINK_NAME -> getDrinkByName(this)
                    }
                }
            }
        }
    }

    override fun showDrinks(drinks: List<Drink>) {
        this.drinks.apply {
            clear()
            addAll(drinks)
            adapter.setDrinks(this)
        }
    }

    override fun showAllFavouriteDrinks(drinks: List<Drink>) {
        this.drinks.apply {
            clear()
            addAll(drinks)
            adapter.setDrinks(this)
        }
    }

    override fun isFavourite(isFavourite: Boolean, position: Int) {
    }

    override fun showError() {
        binding.apply {
            imageEmpty.show()
            textEmpty.show()
        }
    }

    override fun showLoading() {
        binding.progressListDrink.show()
    }

    override fun hideLoading() {
        binding.progressListDrink.hide()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.imageBack) {
            fragmentManager?.let { popFragment(it, this) }
        }
    }

    private fun onClickDrinkItem(id: Int) {
        fragmentManager?.let {
            replaceFragment(
                it, DetailDrinkFragment.getInstance(null, id)
            )
        }
    }

    companion object {
        private const val BUNDLE_DRINKS_FILTER = "bundle drinks filter"
        private const val BUNDLE_FILTER_NAME = "bundle filter name"

        fun getInstance(filterType: String, filterName: String) = ListDrinkFragment().apply {
            arguments =
                bundleOf(BUNDLE_DRINKS_FILTER to filterType, BUNDLE_FILTER_NAME to filterName)
        }
    }
}
