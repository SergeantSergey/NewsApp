package com.example.newsapp.feature.main.ui

import com.example.newsapp.base.Event
import com.example.newsapp.feature.main.domain.model.ArticleDomainModel

data class ViewState(
    val articleList: List<ArticleDomainModel>,
    val isLoading: Boolean,
    val errorMessage: String?,
    val isSearchVisible: Boolean,
    val searchText: String
)

sealed class UiEvent : Event {
    object GetCurrentNews : UiEvent()
    object OnSearchClick : UiEvent()
    data class OnArticleClick(
        val articleDomainModel: ArticleDomainModel
    ) : UiEvent()
}

sealed class DataEvent : Event {
    object OnLoadData : DataEvent()
    data class SuccessNewsRequest(val articleList: List<ArticleDomainModel>) : DataEvent()
    data class ErrorNewsRequest(val error: String) : DataEvent()
}