package com.safelevel.motiv8aiha.repo

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.safelevel.motiv8aiha.model.FeedItem
import com.safelevel.motiv8aiha.network.Motiv8AiWebSocket

class MainRepository() {
    private val gson = Gson()
    private var isRunning = false
    private var filter = Double.MAX_VALUE
    val feedLiveData = MutableLiveData<FeedItem>()
    val feedCachingLiveData = MutableLiveData<ArrayList<FeedItem>>()
    val feedCachingList = ArrayList<FeedItem>()
    private val socket = Motiv8AiWebSocket(onMessageReceived = {
        it?.let {
            val item = gson.fromJson(it, FeedItem::class.java)
            val doubleWeight = item.weight.replace("kg","")
            if(isRunning) {
                if(feedCachingList.isNotEmpty()){
                    feedCachingLiveData.postValue(feedCachingList)
                }
                if(doubleWeight.toDouble() < filter) {
                    feedLiveData.postValue(item)
                }
            }
            else{
                if(doubleWeight.toDouble() < filter) {
                    feedCachingList.add(item)
                }
            }
        }
    })

    fun setFilter(filter : Double){this.filter = filter}

    fun startSocket(){
        if(socket.isOpen.not()) {
            socket.connect()
        }
        isRunning = true
    }

    fun stopSocket(){
        isRunning = false
    }

    fun clearCachingList() = feedCachingList.clear()

    fun isSocketRunning() = isRunning
}