package com.example.newsapp.feature.main.data.api

import com.example.newsapp.feature.main.data.toDomain
import com.example.newsapp.feature.main.domain.model.ArticleDomainModel

class NewsRepositoryImpl(
    private val newsRemoteSource: NewsRemoteSource
) : NewsRepository {

    override suspend fun getTopNews(): List<ArticleDomainModel> {
        return newsRemoteSource.getTopNews().toDomain().articles
    }
}