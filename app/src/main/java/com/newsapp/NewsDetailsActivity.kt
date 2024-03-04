package com.newsapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.IntentCompat
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.newsapp.model.api.ArticlesItem
import com.newsapp.model.api.SourcesItem
import com.newsapp.ui.theme.NewsAppTheme
import com.newsapp.ui.theme.green
import com.newsapp.ui.theme.textColor

class NewsDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                NewsDetailsContent(intent)
                // A surface container using the 'background' color from the theme

            }
        }
    }
}

@Composable
fun NewsDetailsContent(intent:Intent) {
    val article=IntentCompat.getParcelableExtra(intent,"article",ArticlesItem::class.java)

    Column {
        NewDetailsToolBar(article!!)
        Spacer(modifier = Modifier.padding(24.dp))
        NewsDetailsCard(article)


    }


    }
    


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun NewsDetailsContentPreview() {

    //NewsDetailsContent()
    
    
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewDetailsToolBar(model:ArticlesItem) {
    TopAppBar(title = {
        Text(
            text = "${model.source?.name}",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = green,
            titleContentColor = Color.White
        ),
        modifier = Modifier.clip(
            RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 0.dp,
                bottomEnd = 30.dp,
                bottomStart = 30.dp
            )
        )) }
    


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun NewDetailsToolBarPreview() {

    
}
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsDetailsCard(model: ArticlesItem) {


LazyColumn(){
    item {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
        ,
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
        Card(model = model)


    }}}
}



@Composable
fun Card(model: ArticlesItem) {
    val context= LocalContext.current
    Card(
        modifier = Modifier
            .padding(24.dp)
        ,
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Text(text = model.description?:"",
            color = textColor
        )
        Text(text = "View Full Article", color = textColor
            , modifier = Modifier
                .padding(16.dp)
                .align(Alignment.End)
                .clickable {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(model.url))
                    context.startActivity(intent)

                })

    }
}



//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun CardPreview() {
//    Card()
//
//}