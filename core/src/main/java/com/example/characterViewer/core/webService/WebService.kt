package com.example.characterViewer.core.webService

import com.example.characterViewer.core.model.SearchResult
import com.example.characterViewer.core.util.CommonResult

interface WebService {
    suspend fun search(query: String): CommonResult<SearchResult, Error>
}