package com.example

import com.example.auratestapp.databinding.ItemBootBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class BootInfoViewHolder(private val binding: ItemBootBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(data: Pair<String, Int>) = with(binding) {
        root.text = "${data.first} - ${data.second}"
    }

    companion object {
        fun createHolder(parent: ViewGroup): BootInfoViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemBootBinding.inflate(inflater, parent, false)
            return BootInfoViewHolder(binding)
        }
    }
}