package com.example.cocktailmaster.ui.ingredient

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailmaster.data.model.Ingredient
import com.example.cocktailmaster.databinding.ItemIngredientBinding

class IngredientAdapter : RecyclerView.Adapter<IngredientAdapter.ViewHolder>() {

    private val ingredients = mutableListOf<Ingredient>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IngredientAdapter.ViewHolder {

        val binding =
            ItemIngredientBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: IngredientAdapter.ViewHolder, position: Int) {
        holder.bindView(ingredients[position])
    }

    override fun getItemCount() = ingredients.size

    fun setIngredients(ingredients: List<Ingredient>) {
        this.ingredients.apply {
            clear()
            addAll(ingredients)
        }
        notifyDataSetChanged()
    }

    class ViewHolder(
        itemView: View,
        private val binding: ItemIngredientBinding) :
        RecyclerView.ViewHolder(itemView) {

            fun bindView(ingredient: Ingredient) {
                binding.textIngredient.text  = ingredient.name
            }
    }
}
