package com.newsapp.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.newsapp.R
import com.newsapp.ui.theme.green

@Composable
fun NavigationDrawerSheet(onSettingsClick:()->Unit,onCategoryClick:()->Unit) {
    ModalDrawerSheet(
        modifier = Modifier.fillMaxWidth(.6f),
        drawerContainerColor = Color.White
    ) {
        Column(
            modifier =
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(.15f)
                .background(green),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally


        ) {
            Text(
                text = "News App",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        NavigationDrawerItem(
            R.drawable.ic_list,
            "categories", onNavigationItemClick = {
                onCategoryClick()
            }
        )
        NavigationDrawerItem(
            R.drawable.ic_settings,
            "settings",
            onNavigationItemClick = {
                onSettingsClick()
            }
        )


    }


}

@Composable
fun NavigationDrawerItem(icon: Int, text: String,onNavigationItemClick:()->Unit) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp).clickable {
        onNavigationItemClick()
    }) {
        Image(painter = painterResource(id = icon), contentDescription = "icon ")
        Spacer(modifier = Modifier.padding(8.dp))
        Text(text = text, fontSize = 24.sp, fontWeight = FontWeight.Bold)
    }


}

@Preview
@Composable
fun NavigationDrawerItemPreview() {
    NavigationDrawerItem(R.drawable.ic_list, "categories", onNavigationItemClick = {})

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun NavigationDrawerSheetPreview() {
    NavigationDrawerSheet(onCategoryClick = {}, onSettingsClick = {})

}