package com.newsapp.fragments

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.newsapp.NewsDetailsActivity
import com.newsapp.ui.theme.news.NewsViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.newsapp.R
import com.newsapp.model.NewsItem
import com.newsapp.model.api.ArticlesItem
import com.newsapp.ui.theme.textColor


@Composable
fun NewsDetailsScreen(viewModel: NewsViewModel= viewModel(),title:String) {
    val newsItem= viewModel.newsItem.value
    LaunchedEffect(key1 = Unit) {
        viewModel.getNewsItem(title)

    }
    if (newsItem != null) {
         NewsDetailsCard(newsItem = newsItem)
    }
}




@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsDetailsCard(newsItem: ArticlesItem) {


    LazyColumn(){
        item {
            androidx.compose.material3.Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent)

            ) {
                GlideImage(
                    model = newsItem.urlToImage ?: "",
                    contentDescription = "news image",
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(), contentScale = ContentScale.Fit,
                    loading = placeholder(R.drawable.logo)
                )
                Text(
                    text = newsItem.source?.name ?: " ",
                    modifier = Modifier.padding(horizontal = 8.dp), fontSize = 10.sp
                )
                Text(
                    text = newsItem.title ?: "",
                    modifier = Modifier.padding(horizontal = 8.dp),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = newsItem.publishedAt ?: "", modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.End), fontSize = 13.sp
                )
                Card(newsItem = newsItem)


            }
        }}
}



@Composable
fun Card(newsItem: ArticlesItem) {
    val context= LocalContext.current
    androidx.compose.material3.Card(
        modifier = Modifier
            .padding(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Text(
            text = newsItem.description ?: "",
            color = textColor
        )
        Text(text = "View Full Article", color = textColor, modifier = Modifier
            .padding(16.dp)
            .align(Alignment.End)
            .clickable {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(newsItem.url))
                context.startActivity(intent)

            })

    }
}