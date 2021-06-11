package com.example.cocktailmaster.ui.ingredient

import android.view.View
import androidx.appcompat.widget.SearchView
import com.example.cocktailmaster.R
import com.example.cocktailmaster.base.BaseFragment
import com.example.cocktailmaster.data.model.Ingredient
import com.example.cocktailmaster.databinding.FragmentIngredientsBinding
import com.example.cocktailmaster.ui.*
import com.example.cocktailmaster.ui.listdrink.ListDrinkFragment
import com.example.cocktailmaster.utils.AsynctaskState
import com.example.cocktailmaster.utils.ModelConstant
import java.util.*

class IngredientFragment :
    BaseFragment<FragmentIngredientsBinding>(FragmentIngredientsBinding::inflate),
    IngredientContract.View,
    View.OnClickListener,
    SearchView.OnQueryTextListener {

    private var presenter: IngredientPresenter? = null
    private val ingredients = mutableListOf<Ingredient>()
    private val adapter = IngredientAdapter(::onClickIngredientItem)

    override fun initViews() {
        binding?.apply {
            recyclerIngredients.adapter = adapter
            imageBack.setOnClickListener(this@IngredientFragment)
            searchIngredient.setOnQueryTextListener(this@IngredientFragment)
        }
    }

    override fun initData() {
        ingredients.clear()
        presenter = IngredientPresenter(this, RepositoryUtils.getIngredientsRepo())
        presenter?.execute()
    }

    override fun showIngredients(ingredients: List<Ingredient>) {
        this.ingredients.addAll(ingredients)
        adapter.setIngredients(this.ingredients)
    }

    override fun showError() {
        binding?.apply {
            imageEmpty.show()
            textEmpty.show()
        }
    }

    override fun showLoading() {
        binding?.progressIngredient?.show()
    }

    override fun hideLoading() {
        binding?.progressIngredient?.hide()
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

    override fun onQueryTextSubmit(query: String?): Boolean {
        filterIngredients(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        filterIngredients(newText)
        return false
    }

    override fun onPause() {
        super.onPause()
        binding?.apply {
            searchIngredient.apply {
                setQuery("", false)
                clearFocus()
            }
        }
        adapter.setIngredients(ingredients)
    }

    private fun filterIngredients(query: String?) {
        val ingredientFilter = mutableListOf<Ingredient>()
        ingredients.forEach {
            if (it.name.contains(query.toString(), ignoreCase = true)) {
                ingredientFilter.add(it)
            }
        }
        adapter.setIngredients(ingredientFilter)
    }

    private fun onClickIngredientItem(ingredient: Ingredient) {
        fragmentManager?.let {
            replaceFragment(
                it, ListDrinkFragment.getInstance(ModelConstant.INGREDIENT, ingredient.name)
            )
        }
    }
}
