package com.example.testtask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.R
import com.example.testtask.data.Offer
import com.example.testtask.databinding.VerticalItemsBinding

class horizontozalAdapter(
    private val offers: List<Offer>
) : RecyclerView.Adapter<horizontozalAdapter.OfferViewHolder>() {

    inner class OfferViewHolder(private val binding: VerticalItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(offer: Offer) {
            binding.Titleview.text = offer.title
            binding.greebutton.text = offer.button?.text ?: "" // Set button text if available

            // Handle visibility for button text
            binding.greebutton.visibility = if (offer.button != null) View.VISIBLE else View.GONE

            // Click listener for the whole card
            binding.link.setOnClickListener {
                // Open the link or perform desired action
                // You can use an Intent to handle this
            }

            // Optionally set an icon or handle dynamic content for the ImageView
            binding.imageview.setImageResource(R.drawable.star) // Example static icon
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val binding = VerticalItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OfferViewHolder(binding)
    }

    override fun getItemCount(): Int = offers.size

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        holder.bind(offers[position])
    }
}
