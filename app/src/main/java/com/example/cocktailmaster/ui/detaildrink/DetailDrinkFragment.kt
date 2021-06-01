package com.example.cocktailmaster.ui.detaildrink

import android.content.res.Resources
import android.view.View
import androidx.core.os.bundleOf
import com.example.cocktailmaster.R
import com.example.cocktailmaster.base.BaseFragment
import com.example.cocktailmaster.data.model.Drink
import com.example.cocktailmaster.databinding.FragmentDrinkDetailBinding
import com.example.cocktailmaster.ui.popFragment
import com.example.cocktailmaster.utils.loadImage

class DetailDrinkFragment :
    BaseFragment<FragmentDrinkDetailBinding>(FragmentDrinkDetailBinding::inflate),
    View.OnClickListener {

    private val drink by lazy { arguments?.getParcelable<Drink>(BUNDLE_DRINK_DETAIL) }
    private val adapter = IngredientDetailAdapter()

    override fun initViews() {
        binding.apply {
            listOf(textIngredients, textInstruction, imageBack).forEach {
                it.setOnClickListener(this@DetailDrinkFragment)
            }
            drink?.let {
                imageDrink.loadImage(it.thumb)
                textDrinkName.text = it.name
                textDrinkInfor.text = context?.resources?.getString(
                    R.string.text_des_drink,
                    it.alcoholic,
                    it.category,
                    it.glass
                )
                recyclerIngredientDetailInclude.recyclerIngredient.adapter = adapter
                textInstructionDetail.text = it.instruction
            }
        }
    }

    override fun initData() {
        val detailIngredients = mutableListOf<IngredientDetail>()
        drink?.ingredients?.forEachIndexed { index, item ->
            detailIngredients.add(IngredientDetail(item, drink?.measure?.get(index).toString()))
        }
        adapter.setDetailIngredients(detailIngredients);
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.textIngredients -> {
                setColorTabInfor(android.R.color.black, R.color.color_olso_grey, true)
                binding.apply {
                    recyclerIngredientDetailInclude.root.visibility = View.VISIBLE
                    textInstructionDetail.visibility = View.GONE
                }
            }

            R.id.textInstruction -> {
                setColorTabInfor(R.color.color_olso_grey, android.R.color.black, false)
                binding.apply {
                    recyclerIngredientDetailInclude.root.visibility = View.GONE
                    textInstructionDetail.visibility = View.VISIBLE
                }
            }

            R.id.imageBack -> fragmentManager?.let { popFragment(it, this) }
        }
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

    companion object {
        private const val BUNDLE_DRINK_DETAIL = "bundle drink detail"

        fun getInstance(drink: Drink) = DetailDrinkFragment().apply {
            arguments = bundleOf(BUNDLE_DRINK_DETAIL to drink)
        }
    }
}
