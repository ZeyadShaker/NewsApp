package com.newsapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIManager {
    private val retrofit:Retrofit=Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    fun getNewsServices():NewsServices{
        return retrofit.create(NewsServices::class.java)
    }
}