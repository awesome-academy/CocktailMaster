package com.example.cocktailmaster.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailmaster.R
import com.example.cocktailmaster.data.model.Drink
import com.example.cocktailmaster.databinding.ItemDrinkBinding
import com.example.cocktailmaster.utils.loadImage

class RandomDrinksAdapter(
    private val onClickItem: (Drink) -> Unit
) : RecyclerView.Adapter<RandomDrinksAdapter.ViewHolder>() {
    private var drinks = mutableListOf<Drink>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemDrinkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root, binding, parent.context, onClickItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(drinks[position])
    }

    override fun getItemCount() = drinks.size

    fun setDrinks(drinks: List<Drink>) {
        this.drinks.apply {
            clear()
            addAll(drinks)
            notifyDataSetChanged()
        }
    }

    class ViewHolder(
        itemView: View,
        private val binding: ItemDrinkBinding,
        private val context: Context,
        private val onClickItem: (Drink) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private var drinkItem: Drink? = null

        init {
            itemView.setOnClickListener {
                drinkItem?.let { onClickItem(it) }
            }
        }

        fun bindView(drink: Drink) {
            drinkItem = drink
            binding.apply {
                imageDrink.loadImage(drink.thumb)
                textDrinkTitle.text = drink.name
                drink.apply {
                    textDrinkDes.text = context.resources.getString(
                        R.string.text_des_drink,
                        alcoholic,
                        category,
                        glass
                    )
                }
            }
        }
    }
}
