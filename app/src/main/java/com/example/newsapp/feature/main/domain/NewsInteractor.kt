package com.example.newsapp.feature.main.domain

import com.example.newsapp.base.attempt
import com.example.newsapp.feature.main.data.api.NewsRepository

class NewsInteractor(
    private val newsRepository: NewsRepository
) {

    suspend fun getTopNews() = attempt {
        newsRepository.getTopNews()
    }
}