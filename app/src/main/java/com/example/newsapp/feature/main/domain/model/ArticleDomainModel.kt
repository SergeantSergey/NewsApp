package com.example.newsapp.feature.main.domain.model

data class ArticleDomainModel(
    val source: SourceDomainModel,

    val author: String,

    val title: String,

    val description: String,

    val url: String,

    val urlToImage: String,

    val publishedAt: String,

    val content: String,
)