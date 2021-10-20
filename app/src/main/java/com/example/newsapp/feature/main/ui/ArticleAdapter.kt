package com.example.newsapp.feature.main.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.feature.main.domain.model.ArticleDomainModel

class ArticleAdapter(
    private val listArticle: MutableList<ArticleDomainModel>
) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    class ArticleViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        var tvContent: TextView? = null

        init {
            tvContent = item.findViewById<TextView>(R.id.tvContent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_article, parent, false)
        return ArticleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.tvContent?.text = listArticle[position].description
    }

    override fun getItemCount(): Int = listArticle.size

    fun add(listArticle: List<ArticleDomainModel>) {
        this.listArticle.addAll(listArticle)
        notifyDataSetChanged()
    }
}