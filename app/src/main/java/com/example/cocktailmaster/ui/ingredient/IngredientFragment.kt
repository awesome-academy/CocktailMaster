package com.example.cocktailmaster.ui.ingredient

import android.view.View
import com.example.cocktailmaster.R
import com.example.cocktailmaster.base.BaseFragment
import com.example.cocktailmaster.data.model.Ingredient
import com.example.cocktailmaster.databinding.FragmentIngredientsBinding
import com.example.cocktailmaster.ui.RepositoryUtils
import com.example.cocktailmaster.ui.hide
import com.example.cocktailmaster.ui.popFragment
import com.example.cocktailmaster.ui.show

class IngredientFragment :
    BaseFragment<FragmentIngredientsBinding>(FragmentIngredientsBinding::inflate),
    IngredientContract.View,
    View.OnClickListener {

    private var presenter: IngredientPresenter? = null
    private val ingredients = mutableListOf<Ingredient>()
    private val adapter = IngredientAdapter()

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
}
