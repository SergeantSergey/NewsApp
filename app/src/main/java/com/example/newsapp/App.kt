package com.example.newsapp

import android.app.Application
import com.example.newsapp.di.appModule
import com.example.newsapp.di.databaseModule
import com.example.newsapp.feature.bookmarks.di.bookmarkModule
import com.example.newsapp.feature.main.di.mainScreenModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        // start Koin!
        startKoin {
            androidLogger()
            // declare used Android context
            androidContext(this@App)
            // declare modules
            modules(appModule, databaseModule, mainScreenModule, bookmarkModule)
        }

        // log
        Timber.plant(Timber.DebugTree())
    }
}