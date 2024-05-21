package com.newsapp.fragments

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.newsapp.model.api.ArticlesItem
import com.newsapp.ui.theme.news.NewsViewModel
import com.newsapp.utils.NewsCard
import com.newsapp.utils.NewsSourcesTabRows
import androidx.lifecycle.viewmodel.compose.viewModel
import com.newsapp.ui.theme.green

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Newsfragment(
    modifier: Modifier = Modifier,
    category: String
    , viewModel: NewsViewModel = viewModel()
    ,onNewsClick:  (String) -> Unit
) {
    if (viewModel.isLoading.value){
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator(color = green)

        }
    }



    Column(modifier=modifier) {
        NewsSourcesTabRows(category =category, ){sourceId->
            viewModel.getNewsBySource(sourceId)

        }
        NewsList(viewModel.newsListState.toList()){
            onNewsClick(it)

        }

    }
    if (viewModel.messageState.value.isNotEmpty()){
        AlertDialog(onDismissRequest = {viewModel.messageState.value=""}, confirmButton = {
            TextButton(onClick = { viewModel.messageState.value="" }) {
                Text(text = "OK")

            }
        }, title = {
            Text(text = viewModel.messageState.value)
        })

    }




}

@Composable
fun NewsList(newsList:List<ArticlesItem>,onNewsClick:  (String)->Unit) {

    LazyColumn {
        items(newsList.size) { position ->
            NewsCard(newsItem = newsList[position]){title->
                onNewsClick(title)

            }


        }
    }

}