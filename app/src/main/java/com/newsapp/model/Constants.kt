package com.newsapp.model

import com.newsapp.R

object Constants {
    val API_KEY="1f855e0479354e1e962c0dca1aec1194"
    val categories= listOf(
        Category(
            "sports", R.drawable.sports
            ,R.string.sports,R.color.red
        ),
        Category(
            "technology", R.drawable.politics
            ,R.string.technology,R.color.blue
        ),
        Category(
            "health", R.drawable.health
            ,R.string.health,R.color.pink
        ),
        Category(
            "business", R.drawable.bussines
            ,R.string.business,R.color.brown
        ),
        Category(
            "general", R.drawable.environment
            ,R.string.general,R.color.baby_blue
        ),
        Category(
            "science", R.drawable.science
            ,R.string.science,R.color.yellow
        )
    )
}