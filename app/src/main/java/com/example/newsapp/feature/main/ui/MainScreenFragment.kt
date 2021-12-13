package com.example.newsapp.feature.main.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.feature.web.ui.WebActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainScreenFragment : Fragment() {

    private val viewModel by viewModel<MainScreenViewModel>()

    private val articleAdapter: ArticleAdapter by lazy {
        ArticleAdapter(
            listArticle = mutableListOf(),
            favoriteListArticle = mutableListOf(),
            onClick = { articleDomainModel ->
                val intent = Intent(requireActivity(), WebActivity::class.java)
                intent.putExtra(WebActivity.URL, articleDomainModel.url)
                startActivity(intent)
            },
            onFavoriteClick = { articleDomainModel, isFavorite ->
                viewModel.processUiEvent(UiEvent.OnFavoriteClick(articleDomainModel, isFavorite))
            }
        )
    }

    private lateinit var progressBar: ProgressBar
    private lateinit var tvError: TextView
    private lateinit var frameSearch: FrameLayout
    private lateinit var searchEditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mainscreen, container, false)
    }

    private fun initRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvArticle)
        progressBar = view.findViewById(R.id.pbLoading)
        tvError = view.findViewById(R.id.tvError)
        frameSearch = view.findViewById(R.id.frameSearch)
        searchEditText = view.findViewById(R.id.etSearch)
        recyclerView.adapter = articleAdapter
        recyclerView.layoutManager = GridLayoutManager(
            requireContext(),
            resources.getInteger(R.integer.news_list_column_count)
        )

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.processUiEvent(UiEvent.OnSearchTextInput(p0.toString()))
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        recyclerView.addItemDecoration(ArticleItemDecoration())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView(view)

        view.findViewById<ImageView>(R.id.btnSearch).setOnClickListener {
            viewModel.processUiEvent(UiEvent.OnSearchClick)
        }

        viewModel.viewState.observe(viewLifecycleOwner, ::render)
    }

    private fun updateLoading(isLoading: Boolean) {
        progressBar.isGone = !isLoading
    }

    private fun updateErrorText(errorMessage: String?) {
        with(tvError) {
            isVisible = errorMessage != null
            text = errorMessage
        }
    }

    private fun render(viewState: ViewState) {
        updateLoading(viewState.isLoading)
        updateErrorText(viewState.errorMessage)
        showResult(viewState)
        articleAdapter.addFavorite(viewState.favoriteList)
        frameSearch.isGone = !viewState.isSearchVisible
    }

    private fun showResult(viewState: ViewState) {
        if (viewState.isSearchVisible) {
            articleAdapter.add(viewState.searchResult)
        } else {
            articleAdapter.add(viewState.articleList)
        }
    }
}