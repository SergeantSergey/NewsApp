package com.example.newsapp.feature.bookmarks.ui

import com.example.newsapp.base.BaseViewModel
import com.example.newsapp.base.Event
import com.example.newsapp.feature.bookmarks.domain.BookmarkInteractor

class BookmarksScreenViewModel(
    private val boolmarkInteractor: BookmarkInteractor
) : BaseViewModel<ViewState>() {
    override fun initialViewState(): ViewState {
        return ViewState(
            articleList = listOf()
        )
    }

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is DataEvent.SuccessNewsRequest -> {

            }
        }

        return null
    }
}