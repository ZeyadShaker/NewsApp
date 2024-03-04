package com.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.newsapp.api.APIManager
import com.newsapp.fragments.CategoriesFragment

import com.newsapp.fragments.Newsfragment
import com.newsapp.fragments.SearchFragment
import com.newsapp.fragments.SettingsFragment
import com.newsapp.model.CategoriesScreen
import com.newsapp.model.Constants


import com.newsapp.model.NewsScreen
import com.newsapp.model.SearchScreen
import com.newsapp.model.SettingsScreen
import com.newsapp.model.api.ArticlesItem
import com.newsapp.model.api.ArticlesResponse
import com.newsapp.ui.theme.NewsAppTheme
import com.newsapp.utils.NavigationDrawerSheet
import com.newsapp.utils.NewsCard
import com.newsapp.utils.NewsSourcesTabRows
import com.newsapp.utils.NewsTopAppBar
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                // A surface container using the 'background' color from the theme
                NewsScreenContent()
            }
        }
    }
}

@Composable
fun NewsScreenContent() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    val titleToolbar = remember {
        mutableIntStateOf(R.string.news_app)
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent =
        {
            NavigationDrawerSheet(onCategoryClick = {
                navController.popBackStack()
                if (navController.currentDestination?.route != CategoriesScreen().route) {
                    navController.navigate(CategoriesScreen().route)
                }

                scope.launch {
                    drawerState.close()
                }


            },
                onSettingsClick = {
                    navController.popBackStack()
                    if (navController.currentDestination?.route != SettingsScreen().route) {
                        navController.navigate(SettingsScreen().route)
                    }
                    scope.launch {
                        drawerState.close()
                    }

                })
        }) {
        Scaffold(topBar = {
            NewsTopAppBar(titleResID = titleToolbar.intValue) {
                scope.launch {
                    drawerState.open()
                }


            }
        }, modifier = Modifier.fillMaxSize()) { PaddingValues ->

            NavHost(
                navController = navController,
                startDestination = CategoriesScreen().route,
                modifier = Modifier.padding(top = PaddingValues.calculateTopPadding())
            ) {
                composable(CategoriesScreen().route) {
                    titleToolbar.intValue = R.string.news_app
                    CategoriesFragment(navController)
                }
                composable(
                    "${NewsScreen().route}/{category_name}/{category_id}",
                    arguments = listOf(navArgument("category_id") {
                        type = NavType.StringType

                    }, navArgument("category_name") {
                        type = NavType.IntType
                    })
                ) { navBackStackEntry ->
                    val categoryId = navBackStackEntry.arguments?.getString("category_id")
                    val categoryName = navBackStackEntry.arguments?.getInt("category_name")
                    titleToolbar.intValue = categoryName ?: R.string.news_app

                    Newsfragment(category = categoryId ?: "", navController = navController)

                }
                composable(SettingsScreen().route) {
                    titleToolbar.intValue = R.string.settings
                    SettingsFragment()
                }
                composable(SearchScreen().route){
SearchFragment()                }


            }

        }
    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NewsCardPreview() {

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NewsFragmentPreview() {
    Newsfragment(category = "", navController = rememberNavController())
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NewScreenPreview() {
    NewsScreenContent()

}



