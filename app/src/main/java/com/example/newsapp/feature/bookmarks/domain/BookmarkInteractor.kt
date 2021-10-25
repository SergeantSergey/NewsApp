package com.example.newsapp.feature.bookmarks.domain

import com.example.newsapp.feature.bookmarks.data.BookmarksRepository
import com.example.newsapp.feature.main.domain.model.ArticleDomainModel

class BookmarkInteractor(private val bookmarkRepository: BookmarksRepository) {

    suspend fun getAll(): List<ArticleDomainModel> {
        return bookmarkRepository.getAll()
    }

    suspend fun insert(bookmark: ArticleDomainModel) {
        this.bookmarkRepository.insert(bookmark)
    }

    suspend fun insertAll(bookmarks: List<ArticleDomainModel>) {
        bookmarkRepository.insertAll(bookmarks)
    }

    suspend fun update(bookmark: ArticleDomainModel) {
        this.bookmarkRepository.insert(bookmark)
    }

    suspend fun delete(bookmark: ArticleDomainModel) {
        this.bookmarkRepository.delete(bookmark)
    }
}