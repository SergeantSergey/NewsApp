package com.example.newsapp.feature.bookmarks.data

import com.example.newsapp.feature.main.domain.model.ArticleDomainModel

interface BookmarksRepository {

    suspend fun getAll(): List<ArticleDomainModel>

    suspend fun insert(bookmark: ArticleDomainModel)

    suspend fun insertAll(bookmarks: List<ArticleDomainModel>)

    suspend fun update(bookmark: ArticleDomainModel)

    suspend fun delete(bookmark: ArticleDomainModel)
}