package com.example.characterViewer.core.remoteApi

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactoryBase(private val okHttpClient: OkHttpClient) {

    fun <T> provideRetrofit(baseUrl: String, api: Class<T>): T {
        Retrofit.Builder().baseUrl(baseUrl).client(okHttpClient).addConverterFactory(
            GsonConverterFactory.create()
        ).build().also {
            return it.create(api)
        }
    }
}