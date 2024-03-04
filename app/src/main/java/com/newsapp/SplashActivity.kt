package com.newsapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.newsapp.ui.theme.NewsAppTheme

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                Handler(mainLooper).postDelayed({
                    val intent = Intent(this, NewsActivity::class.java)
                    startActivity(intent)
                    finish()
                }, 2500)

                SplashContent()
            }
        }
    }
}

@Composable
fun SplashContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.pattern),
                contentScale = ContentScale.FillBounds
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1F))
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier.fillMaxHeight(.3F),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.weight(1F))
        Image(
            painter = painterResource(id = R.drawable.signature),
            contentDescription = "signature",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth(.35f)
        )
    }


}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashPreview() {
    SplashContent()

}