package com.example.newsapp.feature.main.data.api

import com.example.newsapp.feature.main.data.model.TopNewsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    suspend fun getTopHeadlines(

        @Query("apiKey") apiKey: String = "8ae000fb2c2f4526acf92dd37fa31407",

        @Query("country") country: String = "ru"

    ): TopNewsModel
}