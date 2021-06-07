package com.example.cocktailmaster.ui.listdrink

import android.view.View
import androidx.core.os.bundleOf
import com.example.cocktailmaster.R
import com.example.cocktailmaster.base.BaseFragment
import com.example.cocktailmaster.data.model.Drink
import com.example.cocktailmaster.databinding.FragmentListDrinkBinding
import com.example.cocktailmaster.ui.RepositoryUtils
import com.example.cocktailmaster.ui.hide
import com.example.cocktailmaster.ui.popFragment
import com.example.cocktailmaster.ui.show
import com.example.cocktailmaster.utils.ModelConstant

class ListDrinkFragment :
    BaseFragment<FragmentListDrinkBinding>(FragmentListDrinkBinding::inflate),
    ListDrinkContract.View,
    View.OnClickListener {

    private var presenter: ListDrinkPresenter? = null
    private val drinks = mutableListOf<Drink>()
    private val adapter = ListDrinkAdapter()
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
        presenter = ListDrinkPresenter(this, RepositoryUtils.getDrinkRepo())
        presenter?.apply {
            ModelConstant.apply {
                filterName.toString().apply {
                    when (filterType) {
                        CATEGORY -> getListDrinkByCategory(this)
                        INGREDIENT -> getListDrinkByIngredient(this)
                        FIRST_LETTER -> getListDrinkByFirstLetter(this)
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
        binding.apply {
            imageEmpty.hide()
            textEmpty.hide()
        }
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

    companion object {
        private const val BUNDLE_DRINKS_FILTER = "bundle drinks filter"
        private const val BUNDLE_FILTER_NAME = "bundle filter name"

        fun getInstance(filterType: String, filterName: String) = ListDrinkFragment().apply {
            arguments =
                bundleOf(BUNDLE_DRINKS_FILTER to filterType, BUNDLE_FILTER_NAME to filterName)
        }
    }
}
