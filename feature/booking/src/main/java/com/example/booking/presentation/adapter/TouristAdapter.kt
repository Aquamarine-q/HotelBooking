package com.example.booking.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.booking.R
import com.example.booking.databinding.TouristItemBinding

class TouristAdapter(private val context: Context) :
    RecyclerView.Adapter<TouristAdapter.ItemViewHolder>() {

    private val numbers = context.resources.getStringArray(R.array.tourists)
    private var dataset = mutableListOf(numbers[0])
    private var mExpandedPosition = -1

    inner class ItemViewHolder(binding: TouristItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val layout = binding.layout
        val title = binding.title
        val button = binding.button
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout =
            TouristItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun getItemCount() = dataset.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        with(holder) {
            title.text = context.getString(R.string.tourist, item)
            val isExpanded = position == mExpandedPosition
            with(holder) {
                layout.visibility = if (isExpanded) View.VISIBLE else View.GONE
                button.setImageResource(
                    if (isExpanded) R.drawable.baseline_keyboard_arrow_down
                    else R.drawable.baseline_keyboard_arrow_up
                )
                itemView.isActivated = isExpanded
                button.setOnClickListener {
                    mExpandedPosition = if (isExpanded) -1 else position
                    notifyItemChanged(position)
                }
            }
        }
    }

    fun addTourist() {
        dataset.add(numbers[itemCount])
        notifyItemInserted(itemCount)
    }
}