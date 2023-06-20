package com.example.characterViewer.core.remoteApi

import com.example.characterViewer.core.model.SearchResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("/")
    suspend fun search(@Query("q") search : String, @Query("format") format : String = "json") : Response<SearchResult>
}