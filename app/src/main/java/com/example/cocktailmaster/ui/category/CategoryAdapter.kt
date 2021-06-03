package com.example.cocktailmaster.ui.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailmaster.data.model.Category
import com.example.cocktailmaster.databinding.ItemCategoryBinding
import com.example.cocktailmaster.utils.loadImageByResource
import com.example.cocktailmaster.utils.loadImageByUrl

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private val categories = mutableListOf<Category>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ViewHolder {
        val binding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        holder.bindView(categories[position])
    }

    override fun getItemCount() = categories.size

    fun setCategories(categories: List<Category>) {
        this.categories.apply {
            clear()
            addAll(categories)
        }
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View, private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(itemView) {

        fun bindView(category: Category) {
            binding.apply {
                imageCategoryItem.loadImageByResource(category.categoryThumb)
                textCategoryItem.text = category.name
            }
        }
    }
}
