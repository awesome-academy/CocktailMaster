package com.example.cocktailmaster.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailmaster.databinding.AlphabetItemBinding

class AlphabetAdapter : RecyclerView.Adapter<AlphabetAdapter.ViewHolder>() {
    private val alphabets = mutableListOf<Char>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            AlphabetItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root, binding)
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
        private val binding: AlphabetItemBinding
    ) : RecyclerView.ViewHolder(itemView) {
        fun bindView(s: Char) {
            binding.textAlphabetItem.text = s.toString()
        }

    }
}
