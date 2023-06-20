package com.example.characterviewer

import android.app.Application
import com.example.characterViewer.core.remoteApi.ApiConstant
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@App)
            modules(listOf(appModule))
        }

        ApiConstant.IMAGE_SEARCH_BASE_URL = BuildConfig.PHOTOS_API_BASE_URL
        ApiConstant.BASE_URL = BuildConfig.API_BASE_URL
    }
}