package com.example.newsapp.feature.main.data.api

import com.example.newsapp.feature.main.data.model.TopNewsModel

class NewsRemoteSource(
    private val api: NewsApi
) {

    suspend fun getTopNews(): TopNewsModel {
        return api.getTopHeadlines()
    }
}