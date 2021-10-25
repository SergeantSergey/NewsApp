package com.example.newsapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.newsapp.feature.main.ui.MainScreenFragment

class MainAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    companion object {
        private const val SEARCH_ITEM = 1
        private const val MAIN_ITEM = 0
        private const val CHANGE_LANGUAGE_ITEM = 2
        private const val ITEM_COUNT = 1
    }

    override fun getItemCount() = ITEM_COUNT

    override fun createFragment(position: Int): Fragment {
        return when (position) {

            MAIN_ITEM -> MainScreenFragment()

            SEARCH_ITEM -> MainScreenFragment()

            CHANGE_LANGUAGE_ITEM -> MainScreenFragment()

            else -> MainScreenFragment()
        }
    }
}