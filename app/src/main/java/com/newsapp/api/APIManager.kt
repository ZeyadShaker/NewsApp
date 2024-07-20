package com.newsapp.api

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient

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