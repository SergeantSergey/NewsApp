package com.example.newsapp.feature.bookmarks.data

import androidx.room.TypeConverter
import com.example.newsapp.feature.bookmarks.data.local.BookmarkEntity
import com.example.newsapp.feature.main.domain.model.ArticleDomainModel

fun ArticleDomainModel.toEntityModel(): BookmarkEntity {
    return BookmarkEntity(
        url = this.url,
        author = this.author,
        title = this.title,
        description = this.description,
        urlToImage = this.urlToImage ,
        publishedAt = this.publishedAt,
        content = this.content
    )
}

fun BookmarkEntity.toDomainModel(): ArticleDomainModel {
    return ArticleDomainModel(
        url = this.url,
        author = this.author,
        title = this.title,
        description = this.description,
        urlToImage = this.urlToImage ,
        publishedAt = this.publishedAt,
        content = this.content
    )
}