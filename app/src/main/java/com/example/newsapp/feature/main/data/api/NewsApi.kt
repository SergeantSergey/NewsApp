package com.example.newsapp.feature.main.data.api

import com.example.newsapp.feature.main.data.model.TopNewsModel
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    fun getTopHeadlines(

        @Query("apiKey") apiKey: String = "sdfsdf",

        @Query("country") country: String = "ru"

    ): TopNewsModel
}