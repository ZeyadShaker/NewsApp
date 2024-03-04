package com.newsapp.utils.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.newsapp.R
import com.newsapp.api.APIManager
import com.newsapp.fragments.NewsList
import com.newsapp.model.Constants
import com.newsapp.model.api.ArticlesItem
import com.newsapp.model.api.ArticlesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBox() {
    var showSearchBox by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    val newsListState = remember {
        mutableStateListOf<ArticlesItem>()
    }

    if (showSearchBox) {
        // Show search box
        TextField(
            value = searchText,
            onValueChange = { searchText = it },
            trailingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "", modifier = Modifier.clickable {
                        APIManager.getNewsServices()
                            .getNewsBySourceQuery(Constants.API_KEY, searchText)
                            .enqueue(object : Callback<ArticlesResponse> {
                                override fun onResponse(
                                    call: Call<ArticlesResponse>,
                                    response: Response<ArticlesResponse>
                                ) {
                                    newsListState.clear()
                                    val newsList = response.body()?.articles
                                    if (newsList != null) {
                                        newsListState.addAll(newsList.toList())
                                    }


                                }

                                override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
                                    TODO("Not yet implemented")
                                }


                            })

                    }
                )
            },
            placeholder = { Text("Search...") },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(.9F)
                .background(Color.White)
                .padding(8.dp)
                .border(BorderStroke(width = 4.dp, Color.White)),
            shape = RoundedCornerShape(50),
            leadingIcon = {
                Image(painter = painterResource(id = R.drawable.ic_clear),
                    contentDescription = "",
                    modifier = Modifier.clickable {
                        showSearchBox = false

                    })
            },
        )
        NewsList(newsListState.toList())
    } else {
        // Show search icon
        Image(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "icon search",
            modifier = Modifier
                .padding(8.dp)
                .clickable { showSearchBox = true }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchPreview() {
    SearchBox()

}
