package com.example.cocktailmaster.ui.listdrink

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailmaster.R
import com.example.cocktailmaster.data.model.Drink
import com.example.cocktailmaster.databinding.ItemDrinkDetailBinding
import com.example.cocktailmaster.utils.loadImageByUrl

class ListDrinkAdapter(
    private val clickItem: (Drink) -> Unit,
    private val clickFavourite: (Drink, Int) -> Unit
) : RecyclerView.Adapter<ListDrinkAdapter.ViewHolder>() {

    private val drinks = mutableListOf<Drink>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemDrinkDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root, binding, clickItem, clickFavourite, parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(drinks[position])
    }

    override fun getItemCount() = drinks.size

    fun setDrinks(drinks: List<Drink>) {
        this.drinks.apply {
            clear()
            addAll(drinks)
        }
        notifyDataSetChanged()
    }

    @Suppress("DEPRECATION")
    class ViewHolder(
        itemView: View,
        private val binding: ItemDrinkDetailBinding,
        private val clickItem: (Drink) -> Unit,
        private val clickFavourite: (Drink, Int) -> Unit,
        private val context: Context
    ) : RecyclerView.ViewHolder(itemView) {

        private var drink: Drink? = null

        init {
            binding.apply {
                cardDrinkItem.setOnClickListener {
                    drink?.let { it1 -> clickItem(it1) }
                }
                imageFavourite.setOnClickListener {
                    drink?.let { it1 -> clickFavourite(it1, position) }
                }
            }
        }

        fun bindView(drink: Drink) {
            this.drink = drink
            binding.imageDrink.loadImageByUrl(drink.thumb)
            binding.textDrink.text = drink.name
            binding.imageFavourite.setColorFilter(
                context.resources.getColor(
                    if (drink.isFavourite) R.color.color_red_ribbon else android.R.color.black
                )
            )
        }
    }
}
