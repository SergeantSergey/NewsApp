package com.example.newsapp.feature.bookmarks.data

import com.example.newsapp.feature.bookmarks.data.local.BookmarksDao
import com.example.newsapp.feature.main.domain.model.ArticleDomainModel

class BookmarksRepositoryImpl(private val bookmarksDao: BookmarksDao) : BookmarksRepository {

    override suspend fun getAll(): List<ArticleDomainModel> {
        return bookmarksDao.getAll().map { it.toDomainModel() }
    }

    override suspend fun insert(bookmark: ArticleDomainModel) {
        bookmarksDao.insert(bookmark.toEntityModel())
    }

    override suspend fun insertAll(bookmarks: List<ArticleDomainModel>) {
        bookmarksDao.insertAll(bookmarks.map { it.toEntityModel() })
    }

    override suspend fun update(bookmark: ArticleDomainModel) {
        bookmarksDao.insert(bookmark.toEntityModel())
    }

    override suspend fun delete(bookmark: ArticleDomainModel) {
        bookmarksDao.delete(bookmark.toEntityModel())
    }
}