package com.example.number.presentation.adapter

import android.content.res.Resources
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.core.R
import com.example.core.adapter.PagerAdapter
import com.example.number.data.model.Number
import com.example.number.databinding.NumberItemBinding
import com.google.android.material.tabs.TabLayoutMediator

class NumberAdapter(private var dataset: List<Number>) :
    RecyclerView.Adapter<NumberAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(binding: NumberItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.title
        val nutrition = binding.nutrition
        val conditioner = binding.conditioner
        val pool = binding.pool
        val price = binding.price
        val priceForIt = binding.priceForIt
        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout
        val button = binding.button
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout =
            NumberItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun getItemCount() = dataset.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        with(holder) {
            viewPager.adapter = PagerAdapter(item.image_urls)
            TabLayoutMediator(
                tabLayout, viewPager
            ) { tab, position ->
                tab.icon = if (position in 0 until item.image_urls.size) {
                    ContextCompat.getDrawable(viewPager.context, R.drawable.tab_selector)
                } else {
                    throw Resources.NotFoundException("Position not found")
                }
            }.attach()
            button.setOnClickListener {
                button.findNavController().navigate(Uri.parse("hotelBooking://booking"))
            }
            title.text = item.name
            nutrition.text = item.peculiarities[0]
            conditioner.text = item.peculiarities[1]
            if (item.peculiarities.size >= 3) {
                pool.isVisible = true
                pool.text = item.peculiarities[2]
            }
            price.text = String.format(
                "%s %s ₽",
                item.price.toString().substring(0, 3),
                item.price.toString().substring(3, 6)
            )
            priceForIt.text = item.price_per
        }
    }
}