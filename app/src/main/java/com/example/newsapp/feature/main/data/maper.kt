package com.example.newsapp.feature.main.data

import com.example.newsapp.feature.main.data.model.ArticleModel
import com.example.newsapp.feature.main.data.model.SourceModel
import com.example.newsapp.feature.main.data.model.TopNewsModel
import com.example.newsapp.feature.main.domain.model.ArticleDomainModel
import com.example.newsapp.feature.main.domain.model.SourceDomainModel
import com.example.newsapp.feature.main.domain.model.TopNewsDomainModel

fun TopNewsModel.toDomain(): TopNewsDomainModel {
    return TopNewsDomainModel(
        status = "0",
        totalResults = 0,
        articles = this.articles?.map { articleModel ->
            articleModel.toDomain()
        }
    )
}

fun ArticleModel.toDomain(): ArticleDomainModel {
    return ArticleDomainModel(
        source = this.source.toDomain(),
        author = this.author,
        title = this.title,
        description = this.description,
        url = this.url,
        urlToImage = this.urlToImage,
        publishedAt = this.publishedAt,
        content = this.content
    )
}

fun SourceModel.toDomain(): SourceDomainModel {
    return SourceDomainModel(
        id = this.id,
        name = this.name
    )
}