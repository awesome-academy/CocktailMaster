package com.example.cocktailmaster.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailmaster.databinding.ItemAlphabetBinding

class AlphabetAdapter(
    private val clickItem: (String) -> Unit
) : RecyclerView.Adapter<AlphabetAdapter.ViewHolder>() {
    private val alphabets = mutableListOf<Char>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemAlphabetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root, binding , clickItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(alphabets[position])
    }

    override fun getItemCount() = alphabets.size

    fun setAlphabets(alphabets: List<Char>) {
        this.alphabets.apply {
            clear()
            addAll(alphabets)
        }
        notifyDataSetChanged()
    }

    class ViewHolder(
        itemView: View,
        private val binding: ItemAlphabetBinding,
        private val clickItem: (String) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private var alphabet: Char? = null

        init {
            itemView.setOnClickListener {
                alphabet?.let { clickItem(it.toString()) }
            }
        }

        fun bindView(s: Char) {
            this.alphabet = s
            binding.textAlphabetItem.text = s.toString()
        }

    }
}
