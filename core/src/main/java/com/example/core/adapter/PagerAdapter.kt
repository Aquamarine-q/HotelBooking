package com.example.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.core.databinding.ImageItemBinding

class PagerAdapter(private var dataset: List<String>) :
    RecyclerView.Adapter<PagerAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(binding: ImageItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val photo = binding.photo
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout =
            ImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun getItemCount() = dataset.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        with(holder) {
            Glide.with(itemView.context).load(item).diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(photo)
        }
    }
}