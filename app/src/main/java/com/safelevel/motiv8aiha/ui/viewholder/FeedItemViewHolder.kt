package com.safelevel.motiv8aiha.ui.viewholder

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.safelevel.motiv8aiha.databinding.FeedItemLayoutBinding
import com.safelevel.motiv8aiha.model.FeedItem

class FeedItemViewHolder(private val binding : FeedItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(feedItem: FeedItem){
        binding.feedItem = feedItem
        binding.color = Color.parseColor(feedItem.bagColor)
    }
}