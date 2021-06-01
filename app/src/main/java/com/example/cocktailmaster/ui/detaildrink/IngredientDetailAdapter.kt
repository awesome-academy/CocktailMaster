package com.example.cocktailmaster.ui.detaildrink

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailmaster.databinding.ItemIngredientDetailBinding

class IngredientDetailAdapter : RecyclerView.Adapter<IngredientDetailAdapter.ViewHolder>() {

    private val ingredients = mutableListOf<IngredientDetail>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val binding =
            ItemIngredientDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(ingredients[position])
    }

    override fun getItemCount() = ingredients.size

    fun setDetailIngredients(ingredients: List<IngredientDetail>) {
        this.ingredients.apply {
            clear()
            addAll(ingredients)
        }
        notifyDataSetChanged()
    }

    class ViewHolder(
        itemView: View,
        private val binding: ItemIngredientDetailBinding
    ) : RecyclerView.ViewHolder(itemView) {

        fun bindView(ingredientDetail: IngredientDetail) {
            binding.apply {
                textIngredient.text = ingredientDetail.ingredient
                textVolumn.text = ingredientDetail.volumn
            }
        }
    }
}
