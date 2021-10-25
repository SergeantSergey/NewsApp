package com.example.newsapp.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsapp.base.AppDatabase
import com.example.newsapp.feature.bookmarks.data.local.BookmarksDao
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://newsapi.org/v2/"
const val DATA_BASE = "database-news"

val appModule = module {

    single<HttpLoggingInterceptor> {
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

val databaseModule = module {

    single<AppDatabase> {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            DATA_BASE
        ).fallbackToDestructiveMigrationOnDowngrade()
            .build()
    }

    single <BookmarksDao> {
        get<AppDatabase>().bookmarksDao()
    }
}