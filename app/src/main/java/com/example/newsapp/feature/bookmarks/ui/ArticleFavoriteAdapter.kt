package com.example.newsapp.feature.bookmarks.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.feature.main.domain.model.ArticleDomainModel
import java.text.SimpleDateFormat
import java.util.*

class ArticleFavoriteAdapter(
    private val listArticle: MutableList<ArticleDomainModel>,
    private val onClick: (ArticleDomainModel) -> Unit,
    private val onFavoriteClick: (ArticleDomainModel) -> Unit
) : RecyclerView.Adapter<ArticleFavoriteAdapter.ArticleViewHolder>() {

    class ArticleViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        var ivTitle: ImageView = item.findViewById(R.id.ivTitle)
        var tvTitle: TextView = item.findViewById(R.id.tvTitle)
        var tvContent: TextView = item.findViewById(R.id.tvContent)
        var tvUpdatedAt: TextView = item.findViewById(R.id.tvUpdatedAt)
        var ivFavorite: ImageView = item.findViewById(R.id.ivFavorite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_article, parent, false)
        return ArticleViewHolder(itemView)
    }

    @SuppressLint("SimpleDateFormat")
    private fun parseData(date: String): Date? {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        format.timeZone = TimeZone.getTimeZone("GMT");
        return format.parse(date)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        with(holder) {
            tvTitle.text = listArticle[position].title
            tvContent.text = listArticle[position].description
            itemView.setOnClickListener { onClick(listArticle[position]) }
            val date = parseData(listArticle[position].publishedAt)
            tvUpdatedAt.text = holder.itemView.resources.getString(R.string.publish_at, date)
            ivFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
            ivFavorite.setOnClickListener { onFavoriteClick(listArticle[position]) }
            Glide
                .with(holder.itemView)
                .load(listArticle[position].urlToImage)
                .centerCrop()
                .placeholder(R.drawable.ic_baseline_cloud_download_24)
                .error(R.drawable.ic_baseline_error_outline_24)
                .into(ivTitle)
        }
    }

    override fun getItemCount(): Int = listArticle.size

    @SuppressLint("NotifyDataSetChanged")
    fun addFavorite(favoriteListArticle: List<ArticleDomainModel>) {
        this.listArticle.clear()
        this.listArticle.addAll(favoriteListArticle)
        notifyDataSetChanged()
    }
}