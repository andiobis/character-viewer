package com.example.characterViewer.core.searchService

import com.example.characterViewer.core.model.CharacterModel
import com.example.characterViewer.core.model.SearchResult
import com.example.characterViewer.core.remoteApi.ApiConstant
import com.example.characterViewer.core.remoteApi.ApiConstant.IMAGE_SEARCH_BASE_URL
import com.example.characterViewer.core.util.CommonResult
import com.example.characterViewer.core.webService.WebService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


interface SearchService {
    suspend fun search(query: String): StateFlow<CommonResult<List<CharacterModel>, Error>?>
    fun getSearchFlow(): StateFlow<CommonResult<List<CharacterModel>, Error>?>
}

class SearchServiceImpl(private val webService: WebService) : SearchService {

    private val searchFlow: MutableStateFlow<CommonResult<List<CharacterModel>, Error>?> =
        MutableStateFlow(null)

    override suspend fun search(query: String): StateFlow<CommonResult<List<CharacterModel>, Error>?> {
        webService.search(query).fold(success = {
            val map = it.relatedTopics.map { relatedTopics ->

                var url : String? = null

                if (!relatedTopics.icon.url.isNullOrEmpty()) {
                    url = IMAGE_SEARCH_BASE_URL + relatedTopics.icon.url
                }

                CharacterModel(
                    relatedTopics.text.substringBefore("-").trim(),
                    relatedTopics.text.substringAfter('-').trim(),
                    url
                )
            }
            searchFlow.emit(CommonResult.success(map))
        }, failure = {
            searchFlow.emit(CommonResult.error(it))
        })

        return searchFlow
    }

    override fun getSearchFlow(): StateFlow<CommonResult<List<CharacterModel>, Error>?> = searchFlow

}