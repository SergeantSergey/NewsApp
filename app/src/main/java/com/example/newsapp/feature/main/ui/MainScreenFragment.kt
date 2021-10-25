package com.example.newsapp.feature.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainScreenFragment : Fragment() {

    private val viewModel by viewModel<MainScreenViewModel>()

    private val articleAdapter: ArticleAdapter by lazy {
        ArticleAdapter(
            listArticle = mutableListOf(),
            onClick = { articleDomainModel ->
                viewModel.processUiEvent(UiEvent.OnArticleClick(articleDomainModel))
            }
        )
    }

    private lateinit var progressBar: ProgressBar
    private lateinit var tvError: TextView

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
        recyclerView.adapter = articleAdapter
        recyclerView.layoutManager = GridLayoutManager(
            requireContext(),
            resources.getInteger(R.integer.news_list_column_count)
        )
        recyclerView.addItemDecoration(ArticleItemDecoration())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView(view)

        viewModel.viewState.observe(viewLifecycleOwner, ::render)
    }

    private fun updateLoading(isLoading: Boolean) {
        progressBar.isVisible = isLoading
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
        articleAdapter.add(viewState.articleList)
    }
}