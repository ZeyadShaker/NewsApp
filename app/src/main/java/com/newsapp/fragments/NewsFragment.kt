package com.newsapp.fragments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.newsapp.api.APIManager
import com.newsapp.model.Category
import com.newsapp.model.Constants
import com.newsapp.model.api.ArticlesItem
import com.newsapp.model.api.ArticlesResponse
import com.newsapp.utils.NewsCard
import com.newsapp.utils.NewsSourcesTabRows
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun Newsfragment(
    navController: NavHostController,
    modifier: Modifier = Modifier
    ,category: String) {
    val newsListState= remember {
        mutableStateListOf<ArticlesItem>()
    }
    Column(modifier=modifier) {
        NewsSourcesTabRows(category =category, ){sourceId->
            APIManager.getNewsServices()
                .getNewsBySource(Constants.API_KEY,sourceId)
                .enqueue(object : Callback<ArticlesResponse> {
                    override fun onResponse(
                        call: Call<ArticlesResponse>,
                        response: Response<ArticlesResponse>
                    ) {
                        newsListState.clear()
                        val newsList=response.body()?.articles
                        if (newsList?.isNotEmpty()==true){
                            newsListState.addAll(newsList)
                        }
                    }

                    override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                })
        }
        NewsList(newsListState.toList())

    }




}

@Composable
fun NewsList(newsList:List<ArticlesItem>) {

    LazyColumn {
        items(newsList.size) { position ->
            NewsCard(model = newsList[position])


        }
    }

}