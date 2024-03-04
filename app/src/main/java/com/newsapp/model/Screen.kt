package com.newsapp.model

import android.content.ClipDescription
import com.newsapp.R

open class Screen(val route:String,val description:Int)
class NewsScreen:Screen("news", R.string.news_app)
class CategoriesScreen:Screen("categories",R.string.categories)
class SettingsScreen:Screen("settings", R.string.settings)
class SearchScreen:Screen("Search", R.string.search)
