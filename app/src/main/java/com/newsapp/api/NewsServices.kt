package com.newsapp.api

import com.newsapp.model.api.ArticlesResponse
import com.newsapp.model.api.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsServices {
    @GET("top-headlines/sources")
    fun getNewsSources(
        @Query("apiKey") apiKey:String,
        @Query("category") category:String?
    ):Call<SourcesResponse>
    @GET("everything")
    fun getNewsBySource(
        @Query("apiKey") apiKey:String,
        @Query("sources") sourceId:String,
        @Query("q") searchQuery:String?=null,


    ):Call<ArticlesResponse>

    @GET("everything")
    fun changeLanguage(
        @Query("apiKey") apiKey:String,
        @Query("language") language: String
    ): Call<ArticlesResponse>

    @GET("everything")
    fun getNewsBySourceQuery(
        @Query("apiKey") apiKey:String,
        @Query("q") searchQuery:String?=null,


        ):Call<ArticlesResponse>
}

