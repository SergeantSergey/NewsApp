package com.example.newsapp

import android.app.Application
import com.example.newsapp.di.appModule
import com.example.newsapp.feature.language.di.languageScreenModule
import com.example.newsapp.feature.main.di.mainScreenModule
import com.example.newsapp.feature.search.di.searchScreenModule
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
            modules(appModule, mainScreenModule, searchScreenModule, languageScreenModule)
        }

        // log
        Timber.plant(Timber.DebugTree())
    }
}