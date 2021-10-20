package com.example.newsapp.feature.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainScreenFragment : Fragment() {

    private val viewModel by viewModel<MainScreenViewModel>()
    private var articleAdapter: ArticleAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mainscreen, container, false)
    }

    private fun initRecyclerView(view: View) {
        articleAdapter = ArticleAdapter(mutableListOf())
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvArticle)
        recyclerView.adapter = articleAdapter
        recyclerView.layoutManager = LinearLayoutManager(view.context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView(view)

        viewModel.viewState.observe(viewLifecycleOwner, ::render)
    }

    fun render(viewState: ViewState) {
        articleAdapter?.add(viewState.articleList)
        Timber.e("TIMBER -> ${viewState.errorMessage}")
    }
}