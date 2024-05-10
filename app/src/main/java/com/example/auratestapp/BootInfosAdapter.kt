package com.example.auratestapp

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.BootInfoViewHolder

internal class BootsAdapter : ListAdapter<Pair<String, Int>, BootInfoViewHolder>(BootDataDiffCallback()) {

    var data: List<Pair<String, Int>> = emptyList()
        set(value) {
            field = value
            submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BootInfoViewHolder {
        return BootInfoViewHolder.createHolder(parent)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: BootInfoViewHolder, position: Int) {
        holder.bind(data[position])
    }

    private class BootDataDiffCallback : DiffUtil.ItemCallback<Pair<String, Int>>() {
        override fun areItemsTheSame(oldItem: Pair<String, Int>, newItem: Pair<String, Int>): Boolean {
            return oldItem.first == newItem.first
        }

        override fun areContentsTheSame(oldItem: Pair<String, Int>, newItem: Pair<String, Int>): Boolean {
            return oldItem.second == newItem.second
        }
    }
}