package com.example.characterviewer

import com.example.characterViewer.core.remoteApi.RetrofitFactoryBase
import com.example.characterViewer.core.searchService.SearchService
import com.example.characterViewer.core.searchService.SearchServiceImpl
import com.example.characterViewer.core.webService.WebService
import com.example.characterViewer.core.webService.WebServiceImpl
import com.google.gson.Gson
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainActivityViewModel(get()) }

    single { Gson() }
    single { OkHttpClient()}
    single { RetrofitFactoryBase(get()) }
    single { WebServiceImpl(get()) as WebService}
    single { SearchServiceImpl(get()) as SearchService }
}