package com.example.newsapp.feature.main.ui

import com.example.newsapp.base.BaseViewModel
import com.example.newsapp.base.Event
import com.example.newsapp.feature.bookmarks.domain.BookmarkInteractor
import com.example.newsapp.feature.main.domain.NewsInteractor

class MainScreenViewModel(
    val newsInteractor: NewsInteractor,
    val bookMarkInteractor: BookmarkInteractor
) : BaseViewModel<ViewState>() {

    init {
        processUiEvent(UiEvent.GetCurrentNews)
    }

    override fun initialViewState(): ViewState {
        return ViewState(
            articleList = listOf(),
            favoriteList = listOf(),
            searchResult = listOf(),
            isLoading = true,
            errorMessage = null,
            isSearchVisible = false,
            searchText = ""
        )
    }

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {

            is UiEvent.GetCurrentNews -> {
                processDataEvent(DataEvent.OnLoadData)
                processDataEvent(DataEvent.OnLoadFavoriteData)
            }

            is UiEvent.OnSearchClick -> {
                return previousState.copy(isSearchVisible = !previousState.isSearchVisible)
            }

            is UiEvent.OnSearchTextInput -> {
                val result = previousState.articleList.filter { articles ->
                    articles.title.contains(event.searchText)
                }
                return previousState.copy(searchResult = result)
            }

            is UiEvent.OnArticleClick -> {

            }

            is UiEvent.OnFavoriteClick -> {
                if (event.isFavorite) {
                    bookMarkInteractor.delete(event.articleDomainModel)
                } else {
                    bookMarkInteractor.insert(event.articleDomainModel)
                }
                processDataEvent(DataEvent.OnLoadFavoriteData)
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

            is DataEvent.OnLoadFavoriteData -> {
                return previousState.copy(favoriteList = bookMarkInteractor.getAll())
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