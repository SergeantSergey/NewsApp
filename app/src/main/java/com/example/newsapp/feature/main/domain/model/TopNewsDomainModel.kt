package com.example.newsapp.feature.main.domain.model

data class TopNewsDomainModel(

    val status: String,

    val totalResults: Int,

    val articles: List<ArticleDomainModel>,
)