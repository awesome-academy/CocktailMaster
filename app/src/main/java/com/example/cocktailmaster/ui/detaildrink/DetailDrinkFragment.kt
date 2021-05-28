package com.example.cocktailmaster.ui.detaildrink

import androidx.core.os.bundleOf
import com.example.cocktailmaster.R
import com.example.cocktailmaster.base.BaseFragment
import com.example.cocktailmaster.data.model.Drink
import com.example.cocktailmaster.databinding.FragmentDrinkDetailBinding
import com.example.cocktailmaster.utils.loadImage

class DetailDrinkFragment :
    BaseFragment<FragmentDrinkDetailBinding>(FragmentDrinkDetailBinding::inflate) {

    private val drink by lazy { arguments?.getParcelable<Drink>(BUNDLE_DRINK_DETAIL) }

    override fun initViews() {
        binding.apply {
            imageDrink.loadImage(drink?.thumb)
            textDrinkName.text = drink?.name
            textDrinkInfor.text = context?.resources?.getString(
                R.string.text_des_drink,
                drink?.alcoholic,
                drink?.category,
                drink?.glass
            )
        }
    }

    override fun initData() {

    }

    companion object {
        private const val BUNDLE_DRINK_DETAIL = "bundle drink detail"

        fun getInstance(drink: Drink) = DetailDrinkFragment().apply {
            arguments = bundleOf(BUNDLE_DRINK_DETAIL to drink)
        }
    }
}
