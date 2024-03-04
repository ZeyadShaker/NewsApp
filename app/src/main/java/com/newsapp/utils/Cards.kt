package com.newsapp.utils

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.newsapp.NewsDetailsActivity
import com.newsapp.R

import com.newsapp.model.api.ArticlesItem

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun NewsCard(model: ArticlesItem) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                val intent= Intent(context,NewsDetailsActivity::class.java)
                intent.putExtra("article",model)
                context.startActivity(intent)

            },
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)

    ) {
        GlideImage(
            model = model.urlToImage ?: "",
            contentDescription = "news image",
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(), contentScale = ContentScale.Fit,
            loading = placeholder(R.drawable.logo)
        )
        Text(
            text = model.source?.name ?: " ",
            modifier = Modifier.padding(horizontal = 8.dp), fontSize = 10.sp
        )
        Text(
            text = model.title ?: "",
            modifier = Modifier.padding(horizontal = 8.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = model.publishedAt ?: "", modifier = Modifier
                .padding(16.dp)
                .align(Alignment.End), fontSize = 13.sp
        )

    }
}
