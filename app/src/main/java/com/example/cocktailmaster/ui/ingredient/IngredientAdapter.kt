package com.example.cocktailmaster.ui.ingredient

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailmaster.data.model.Ingredient
import com.example.cocktailmaster.databinding.ItemIngredientBinding

class IngredientAdapter(
    private val clickItem: (Ingredient) -> Unit
) : RecyclerView.Adapter<IngredientAdapter.ViewHolder>() {

    private val ingredients = mutableListOf<Ingredient>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val binding =
            ItemIngredientBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root, binding, clickItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
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
        private val binding: ItemIngredientBinding,
        private val clickItem: (Ingredient) -> Unit
    ) :
        RecyclerView.ViewHolder(itemView) {

        private var ingredient: Ingredient? = null

        init {
            itemView.setOnClickListener {
                ingredient?.let { clickItem(it) }
            }
        }

        fun bindView(ingredient: Ingredient) {
            this.ingredient = ingredient
            binding.textIngredient.text = ingredient.name
        }
    }
}
