package com.safelevel.motiv8aiha.network

import retrofit2.Call
import retrofit2.http.GET

interface Motiv8AiService {

    @GET("data/get")
    fun getData() : Call<String>
}