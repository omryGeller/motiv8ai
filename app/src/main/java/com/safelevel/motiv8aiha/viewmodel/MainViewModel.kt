package com.safelevel.motiv8aiha.viewmodel

import androidx.lifecycle.ViewModel
import com.safelevel.motiv8aiha.repo.MainRepository

class MainViewModel(private val repository : MainRepository) : ViewModel() {

    fun listenToFeed() = repository.feedLiveData

    fun listenToCachedFeedItems() = repository.feedCachingLiveData

    fun startSocket() = repository.startSocket()

    fun stopSocket() = repository.stopSocket()

    fun isSocketRunning() = repository.isSocketRunning()

    fun clearCachingList() = repository.clearCachingList()

    fun setFilter(filter : Double) = repository.setFilter(filter)
}