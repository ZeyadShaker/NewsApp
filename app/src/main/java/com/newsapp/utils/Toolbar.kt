package com.newsapp.utils


import android.icu.text.CaseMap.Title
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.newsapp.R
import com.newsapp.model.SearchScreen
import com.newsapp.ui.theme.green
import com.newsapp.utils.compose.SearchBox


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsTopAppBar(titleResID:Int ,onSideMenuClick: () -> Unit) {
    val context= LocalContext.current
    var text = remember {
        mutableStateOf(TextFieldValue(""))
    }
    TopAppBar(
        navigationIcon = {
            Image(
                painter = painterResource(id = R.drawable.ic_menu),
                contentDescription = "icon menu",
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        onSideMenuClick()

                    }

            )


        },
        title = {
            Text(
                text = stringResource(titleResID),
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
        ), actions = {
           SearchBox()
        }
    )

}

@Preview
@Composable
fun NewsTopAppBarPreview() {
    NewsTopAppBar(R.string.news_app,{})


}