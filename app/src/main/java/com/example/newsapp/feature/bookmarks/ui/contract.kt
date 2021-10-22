package com.example.newsapp.feature.bookmarks.ui

import com.example.newsapp.base.Event
import com.example.newsapp.feature.main.domain.model.ArticleDomainModel

data class ViewState(
    val articleList: List<ArticleDomainModel>
)

sealed class DataEvent : Event {
    data class SuccessNewsRequest(
        val articleList: List<ArticleDomainModel>
    ) : DataEvent()
}