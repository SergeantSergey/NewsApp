package com.example.newsapp.feature.bookmarks.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.feature.web.ui.WebActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookmarksScreenFragment private constructor() : Fragment(R.layout.fragment_bookmarks) {

    companion object {
        fun newInstance() = BookmarksScreenFragment()
    }

    private val viewModel by viewModel<BookmarksScreenViewModel>()

    private val articleAdapter: ArticleFavoriteAdapter by lazy {
        ArticleFavoriteAdapter(
            listArticle = mutableListOf(),
            onClick = { articleDomainModel ->
                val intent = Intent(requireActivity(), WebActivity::class.java)
                intent.putExtra(WebActivity.URL, articleDomainModel.url)
                startActivity(intent)
            },
            onFavoriteClick = { articleDomainModel ->
                viewModel.processUiEvent(UiEvent.OnFavoriteClick(articleDomainModel))
            }
        )
    }

    private lateinit var tvEmpty: TextView
    private lateinit var recyclerView: RecyclerView

    private fun initView(view: View) {
        tvEmpty = view.findViewById(R.id.tvNotBookmark)
        recyclerView = view.findViewById(R.id.rvBookmarkArticle)
        recyclerView.addItemDecoration(ArticleFavoriteItemDecoration())
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = articleAdapter
    }

    private fun render(viewState: ViewState) {
        if (viewState.articleFavoriteList.isEmpty()) {
            tvEmpty.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        } else {
            tvEmpty.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
            articleAdapter.addFavorite(viewState.articleFavoriteList)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        viewModel.viewState.observe(viewLifecycleOwner, ::render)
    }
}