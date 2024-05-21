package com.newsapp.ui.theme.news

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.ViewModel
import com.newsapp.api.APIManager
import com.newsapp.model.Constants
import com.newsapp.model.api.ArticlesItem
import com.newsapp.model.api.ArticlesResponse
import com.newsapp.model.api.SourcesItem
import com.newsapp.model.api.SourcesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {
    val newsListState = mutableStateListOf<ArticlesItem>()
    val selectedTabIndex = mutableIntStateOf(0)
    var newsItem=mutableStateOf<ArticlesItem?>(null)

    val sourcesList = mutableStateListOf<SourcesItem>()
    val isLoading= mutableStateOf(false)
    val messageState= mutableStateOf("")

    fun getNewsBySource(sourceId: String) {
        isLoading.value=true
        APIManager.getNewsServices()
            .getNewsBySource(Constants.API_KEY, sourceId)
            .enqueue(object : Callback<ArticlesResponse> {
                override fun onResponse(
                    call: Call<ArticlesResponse>,
                    response: Response<ArticlesResponse>
                ) {
                    isLoading.value=false
                    newsListState.clear()
                    val newsList = response.body()?.articles
                    if (newsList?.isNotEmpty() == true) {
                        newsListState.addAll(newsList)
                    }
                }

                override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
                    isLoading.value=false
                    messageState.value="${t.message}"
                }

            })
    }
    fun getNewsSources(category: String){
        isLoading.value=true
        APIManager
            .getNewsServices()
            .getNewsSources(Constants.API_KEY,category)
            .enqueue(object :Callback<SourcesResponse>{
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    isLoading.value=false
                    val sources=response.body()?.sources
                    if (sources?.isNotEmpty()==true){
                        sourcesList.addAll(sources)
                    }
                }

                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                    isLoading.value=false
                    messageState.value="${t.message}"
                }

            })
    }
    fun getNewsItem(title:String){
        APIManager.getNewsServices().getNewsItem(Constants.API_KEY,title).enqueue(object :Callback<ArticlesResponse>{
            override fun onResponse(
                call: Call<ArticlesResponse>,
                response: Response<ArticlesResponse>
            ) {
                if (response.isSuccessful){
                    val news=response.body()?.articles?.get(0)!!
                newsItem.value=news
                }

            }

            override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
                Log.e("getNewsItem onFailure","${t.message}")
            }

        })
    }

}