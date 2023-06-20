package com.example.characterViewer.core.webService

import com.example.characterViewer.core.model.SearchResult
import com.example.characterViewer.core.remoteApi.ApiConstant
import com.example.characterViewer.core.remoteApi.RetrofitFactoryBase
import com.example.characterViewer.core.remoteApi.SearchApi
import com.example.characterViewer.core.util.CommonResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WebServiceImpl(
    private val retrofitFactoryBase: RetrofitFactoryBase
) : WebService {

    private val searchApi: SearchApi by lazy {
        retrofitFactoryBase.provideRetrofit(ApiConstant.BASE_URL, SearchApi::class.java)
    }

    override suspend fun search(query: String): CommonResult<SearchResult, Error> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val result = searchApi.search(query)

                if (!result.isSuccessful) {
                    return@withContext CommonResult.error(
                        Error(
                            "Error: ${
                                result.errorBody()?.string() ?: "unknown error"
                            }"
                        )
                    )
                }

                val requestBody = result.body()
                    ?: return@withContext CommonResult.error(Error("Error: empty request body"))

                CommonResult.success(requestBody)
            } catch (e: Exception) {
                CommonResult.error(Error(e.localizedMessage, e))
            }
        }

}

