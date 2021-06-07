package com.example.cocktailmaster.ui.detaildrink

import android.view.View
import androidx.core.os.bundleOf
import com.example.cocktailmaster.R
import com.example.cocktailmaster.base.BaseFragment
import com.example.cocktailmaster.data.model.Drink
import com.example.cocktailmaster.databinding.FragmentDrinkDetailBinding
import com.example.cocktailmaster.ui.RepositoryUtils
import com.example.cocktailmaster.ui.hide
import com.example.cocktailmaster.ui.popFragment
import com.example.cocktailmaster.ui.show
import com.example.cocktailmaster.utils.loadImageByUrl

class DetailDrinkFragment :
    BaseFragment<FragmentDrinkDetailBinding>(FragmentDrinkDetailBinding::inflate),
    View.OnClickListener,
    DetailDrinkContract.View {

    private var drink: Drink? = null
    private val adapter = IngredientDetailAdapter()
    private var presenter: DetailDrinkPresenter? = null
    private val id by lazy { arguments?.getInt(BUNDLE_DRINK_ID, 0) }

    override fun initViews() {
        binding.apply {
            listOf(textIngredients, textInstruction, imageBack).forEach {
                it.setOnClickListener(this@DetailDrinkFragment)
            }
            recyclerIngredientDetailInclude.recyclerIngredient.adapter = adapter
        }
    }

    override fun initData() {
        if (id != 0) {
            presenter = DetailDrinkPresenter(this, RepositoryUtils.getDrinkRepo())
            id?.let { presenter?.loadDetailDrink(it) }
        }
        drink = arguments?.getParcelable(BUNDLE_DRINK_DETAIL)
        initTextForDrinkInfor()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.textIngredients -> {
                setColorTabInfor(android.R.color.black, R.color.color_olso_grey, true)
                binding.apply {
                    recyclerIngredientDetailInclude.root.show()
                    textInstructionDetail.hide()
                }
            }

            R.id.textInstruction -> {
                setColorTabInfor(R.color.color_olso_grey, android.R.color.black, false)
                binding.apply {
                    recyclerIngredientDetailInclude.root.hide()
                    textInstructionDetail.show()
                }
            }

            R.id.imageBack -> fragmentManager?.let { popFragment(it, this) }
        }
    }

    override fun showDetailDrink(drink: Drink) {
        this.drink = drink
        initTextForDrinkInfor()
    }

    override fun showError() {
        binding.apply {
            textEmpty.show()
            imageEmpty.show()
        }
    }

    override fun showLoading() {
        binding.progressDrinkLoading.show()
    }

    override fun hideLoading() {
        binding.progressDrinkLoading.hide()
    }

    @Suppress("DEPRECATION")
    private fun setColorTabInfor(
        ingredientColor: Int,
        instructionColor: Int,
        isSystemColor: Boolean
    ) {
        binding.apply {
            if (isSystemColor) {
                viewStraightIngredient.setBackgroundColor(resources.getColor(ingredientColor))
                textIngredients.setTextColor(resources.getColor(ingredientColor))
                viewStraightInstruction.setBackgroundColor(resources.getColor(instructionColor))
                textInstruction.setTextColor(resources.getColor(instructionColor))
            } else {
                viewStraightIngredient.setBackgroundColor(resources.getColor(ingredientColor))
                textIngredients.setTextColor(resources.getColor(ingredientColor))
                viewStraightInstruction.setBackgroundColor(resources.getColor(instructionColor))
                textInstruction.setTextColor(resources.getColor(instructionColor))
            }
        }
    }

    private fun initTextForDrinkInfor() {
        binding.apply {
            drink?.let {
                imageDrink.loadImageByUrl(it.thumb)
                textDrinkName.text = it.name
                textDrinkInfor.text = context?.resources?.getString(
                    R.string.text_des_drink,
                    it.alcoholic,
                    it.category,
                    it.glass
                )
                textInstructionDetail.text = it.instruction
            }
        }

        val detailIngredients = mutableListOf<IngredientDetail>()
        drink?.apply {
            if (ingredients.size <= measure.size) {
                ingredients.forEachIndexed { index, item ->
                    detailIngredients.add(IngredientDetail(item, measure[index]))
                }
            } else {
                measure.forEachIndexed { index, item ->
                    detailIngredients.add(IngredientDetail(ingredients[index], item))
                }
            }
        }
        adapter.setDetailIngredients(detailIngredients);
    }

    companion object {
        private const val BUNDLE_DRINK_DETAIL = "bundle drink detail"
        private const val BUNDLE_DRINK_ID = "bundle drink id"

        fun getInstance(drink: Drink?, id: Int) = DetailDrinkFragment().apply {
            arguments = bundleOf(BUNDLE_DRINK_DETAIL to drink, BUNDLE_DRINK_ID to id)
        }
    }
}
