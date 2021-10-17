package com.example.newsapp.feature.main.data.model

import com.google.gson.annotations.SerializedName

data class TopNewsModel(

    @SerializedName("status")
    val status: String,

    @SerializedName("totalResults")
    val totalResults: Int,

    @SerializedName("articles")
    val articles: List<ArticleModel>,
)