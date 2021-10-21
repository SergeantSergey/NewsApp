package com.example.newsapp.feature.main.ui

import com.example.newsapp.base.BaseViewModel
import com.example.newsapp.base.Event
import com.example.newsapp.feature.main.domain.NewsInteractor

class MainScreenViewModel(val newsInteractor: NewsInteractor) : BaseViewModel<ViewState>() {

    init {
        processUiEvent(UiEvent.GetCurrentNews)
    }

    override fun initialViewState(): ViewState {
        return ViewState(listOf(), true, null)
    }

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {

            is UiEvent.GetCurrentNews -> {
                processDataEvent(DataEvent.OnLoadData)
            }

            is DataEvent.OnLoadData -> {
                newsInteractor.getTopNews().fold(
                    onError = {
                        processDataEvent(
                            DataEvent.ErrorNewsRequest(it.localizedMessage ?: "")
                        )
                    },
                    onSuccess = {
                        processDataEvent(DataEvent.SuccessNewsRequest(it))
                    }
                )
            }

            is DataEvent.SuccessNewsRequest -> {
                return previousState.copy(articleList = event.articleList, isLoading = false)
            }

            is DataEvent.ErrorNewsRequest -> {
                return previousState.copy(isLoading = false, errorMessage = event.error)
            }
        }
        return null
    }
}