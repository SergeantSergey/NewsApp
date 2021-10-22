package com.example.newsapp.feature.bookmarks.ui

import androidx.lifecycle.ViewModel
import com.example.newsapp.base.BaseViewModel
import com.example.newsapp.base.Event

class BookmarksScreenViewModel : BaseViewModel<ViewState>() {
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