package com.newsapp.model

import android.content.ClipDescription
import com.newsapp.R

open class Screen(val route:String,val description:Int)
class NewsScreen:Screen("news", R.string.news_app){
    companion object{
        val ROUTE_NAME="news"
    }
}
class CategoriesScreen:Screen("categories",R.string.categories){
    companion object{
        val ROUTE_NAME="categories"
    }

}
class NewsDetailsScreen:Screen("newsDetails", R.string.news_details){
    companion object{
        val ROUTE_NAME="newsDetails"
    }
}
class SettingsScreen:Screen("settings", R.string.settings)
class SearchScreen:Screen("Search", R.string.search)
