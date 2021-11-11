package com.safelevel.motiv8aiha.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.safelevel.motiv8aiha.databinding.FeedItemLayoutBinding
import com.safelevel.motiv8aiha.model.FeedItem
import com.safelevel.motiv8aiha.ui.viewholder.FeedItemViewHolder

class FeedRecyclerAdapter(private val data : ArrayList<FeedItem>) : RecyclerView.Adapter<FeedItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedItemViewHolder {
        val binding = FeedItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FeedItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeedItemViewHolder, position: Int) {
        val feedItem = data.get(position)
        holder.onBind(feedItem)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addItem(feedItem: FeedItem){
        data.add(0,feedItem)
        notifyItemInserted(0)
    }

    fun addItems(list : ArrayList<FeedItem>){
        data.addAll(0,list)
        notifyItemRangeInserted(0,list.size)
    }
}