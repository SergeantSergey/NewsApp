package com.example.newsapp.feature.bookmarks.ui

import com.example.newsapp.base.Event
import com.example.newsapp.feature.main.domain.model.ArticleDomainModel

data class ViewState(
    val articleFavoriteList: List<ArticleDomainModel>
)

sealed class DataEvent : Event {
    object GetFavoriteNews : UiEvent()
}

sealed class UiEvent : Event {
    data class OnFavoriteClick(
        val articleDomainModel: ArticleDomainModel
    ) : UiEvent()
}