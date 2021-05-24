package com.example.cocktailmaster.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailmaster.R
import com.example.cocktailmaster.data.model.Drink
import com.example.cocktailmaster.databinding.DrinkItemBinding
import com.example.cocktailmaster.utils.loadImage

class RandomDrinksAdapter : RecyclerView.Adapter<RandomDrinksAdapter.ViewHolder>() {
    private var drinks = mutableListOf<Drink>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = DrinkItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root, binding, parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(drinks[position])
    }

    override fun getItemCount() = drinks.size

    fun setDrinks(drinks: List<Drink>) {
        this.drinks.run {
            clear()
            addAll(drinks)
            notifyDataSetChanged()
        }
    }

    class ViewHolder(
        itemView: View,
        private val binding: DrinkItemBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(itemView) {

        fun bindView(drink: Drink) {
            binding.apply {
                imageDrink.loadImage(drink.thumb)
                textDrinkTitle.text = drink.name
                textDrinkDes.text = context.resources.getString(
                    R.string.text_des_drink,
                    drink.alcoholic,
                    drink.category,
                    drink.glass
                )
            }
        }
    }
}
