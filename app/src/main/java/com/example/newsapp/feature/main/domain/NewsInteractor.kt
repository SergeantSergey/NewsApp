package com.example.newsapp.feature.main.domain

import com.example.newsapp.base.attempt
import com.example.newsapp.feature.main.data.api.NewsRepository
import com.example.newsapp.feature.main.domain.model.ArticleDomainModel
import com.example.newsapp.feature.main.domain.model.TopNewsDomainModel

class NewsInteractor(
    private val newsRepository: NewsRepository
) {

    suspend fun getTopNews() = attempt {
        newsRepository.getTopNews()
    }
}