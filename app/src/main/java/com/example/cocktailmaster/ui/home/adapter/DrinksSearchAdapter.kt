package com.example.cocktailmaster.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailmaster.data.model.Drink
import com.example.cocktailmaster.databinding.ItemSearchDrinksBinding

class DrinksSearchAdapter(
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<DrinksSearchAdapter.ViewHolder>() {
    private val drinks = mutableListOf<Drink>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val binding =
            ItemSearchDrinksBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root, binding , onClick)
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

    class ViewHolder(
        itemView: View,
        private val binding: ItemSearchDrinksBinding,
        private val onClick: (String) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private var name = ""

        init {
            itemView.setOnClickListener {
                onClick(name)
            }
        }

        fun bindView(drink: Drink) {
            name = drink.name
            binding.textDrinkItem.text = drink.name
        }
    }
}
