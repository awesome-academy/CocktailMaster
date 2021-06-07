package com.example.cocktailmaster.ui.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailmaster.data.model.Category
import com.example.cocktailmaster.databinding.ItemCategoryBinding
import com.example.cocktailmaster.utils.loadImageByResource

class CategoryAdapter(
    private val clickItem: (Category) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private val categories = mutableListOf<Category>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root, binding, clickItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
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

    class ViewHolder(
        itemView: View,
        private val binding: ItemCategoryBinding,
        private val clickItem: (Category) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private var category: Category? = null

        init {
            itemView.setOnClickListener {
                category?.let { clickItem(it) }
            }
        }

        fun bindView(category: Category) {
            this.category = category
            binding.apply {
                imageCategoryItem.loadImageByResource(category.categoryThumb)
                textCategoryItem.text = category.name
            }
        }
    }
}
