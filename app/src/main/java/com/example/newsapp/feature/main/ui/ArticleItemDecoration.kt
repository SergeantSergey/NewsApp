package com.example.newsapp.feature.main.ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ArticleItemDecoration : RecyclerView.ItemDecoration() {

    companion object {
        private const val MARGIN_TOP = 10
        private const val MARGIN_BOTTOM = 10
        private const val MARGIN_LEFT = 16
        private const val MARGIN_RIGHT = 16
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(MARGIN_LEFT, MARGIN_TOP, MARGIN_RIGHT, MARGIN_BOTTOM)
    }
}