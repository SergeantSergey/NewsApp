package com.example.newsapp.feature.main.data.api

import com.example.newsapp.feature.main.domain.model.TopNewsDomainModel

interface NewsRepository {

    suspend fun getTopNews(): TopNewsDomainModel
}