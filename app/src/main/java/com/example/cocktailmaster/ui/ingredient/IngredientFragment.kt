package com.example.cocktailmaster.ui.ingredient

import android.view.View
import com.example.cocktailmaster.R
import com.example.cocktailmaster.base.BaseFragment
import com.example.cocktailmaster.data.model.Drink
import com.example.cocktailmaster.data.model.Ingredient
import com.example.cocktailmaster.databinding.FragmentIngredientsBinding
import com.example.cocktailmaster.ui.*
import com.example.cocktailmaster.ui.detaildrink.DetailDrinkFragment
import com.example.cocktailmaster.ui.listdrink.ListDrinkFragment
import com.example.cocktailmaster.utils.ModelConstant

class IngredientFragment :
    BaseFragment<FragmentIngredientsBinding>(FragmentIngredientsBinding::inflate),
    IngredientContract.View,
    View.OnClickListener {

    private var presenter: IngredientPresenter? = null
    private val ingredients = mutableListOf<Ingredient>()
    private val adapter = IngredientAdapter(::onClickIngredientItem)

    override fun initViews() {
        binding.apply {
            recyclerIngredients.adapter = adapter
            imageBack.setOnClickListener(this@IngredientFragment)
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
        binding.apply {
            imageEmpty.show()
            textEmpty.show()
        }
    }

    override fun showLoading() {
        binding.progressIngredient.show()
    }

    override fun hideLoading() {
        binding.progressIngredient.hide()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.imageBack) {
            fragmentManager?.let { popFragment(it, this) }
        }
    }

    private fun onClickIngredientItem(ingredient: Ingredient) {
        fragmentManager?.let {
            replaceFragment(
                it, ListDrinkFragment.getInstance(ModelConstant.INGREDIENT , ingredient.name)
            )
        }
    }
}
