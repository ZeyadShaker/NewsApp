package com.newsapp.model.api

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ArticlesResponse(

	@field:SerializedName("totalResults")
	val totalResults: Int? = null,

	@field:SerializedName("articles")
	val articles: List<ArticlesItem>? = null,

	@field:SerializedName("status")
	val status: String? = null
) : Parcelable