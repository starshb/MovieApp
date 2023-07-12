package kr.co.move.part4plus.movieapp.library.network.retrofit

import com.google.gson.Gson
import kr.co.move.part4plus.movieapp.library.network.api.ApiService
import kr.co.move.part4plus.movieapp.library.network.model.ApiResponse
import kr.co.move.part4plus.movieapp.library.network.model.ApiResult
import kr.co.move.part4plus.movieapp.library.network.model.NetworkRequestInfo
import kr.co.move.part4plus.movieapp.library.network.model.RequestType
import java.lang.reflect.Type
import javax.inject.Inject

class NetworkRequestFactoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val gson: Gson,
    private val headerParser: HeaderParser
) : NetworkRequestFactory {

    companion object {
        private const val KEY_HEADER_CONTENT_TYPE = "Content-Type"
        private const val CONTENT_TYPE_JSON = "application/json"
        private const val TYPE_XML = "xml"
    }

    override suspend fun <T> create(
        url: String,
        requestInfo: NetworkRequestInfo,
        type: Type
    ): ApiResult<T> {

        val headerMap = hashMapOf<String, String>().apply {
            put(KEY_HEADER_CONTENT_TYPE, CONTENT_TYPE_JSON)
            putAll(requestInfo.headers)
        }

        return try {
            val response = when (requestInfo.method) {
                RequestType.GET -> performGetRequest(url, headerMap)
                RequestType.POST -> performPostRequest(url, requestInfo.body, headerMap)
                RequestType.PUT -> performPutRequest(url, requestInfo.body, headerMap)
                RequestType.DELETE -> performDeleteRequest(url, requestInfo.body, headerMap)
            }

            val responseHeaders = headerParser.parseHeadersToMap(response.headers())
            val responseCode = response.code()

            val apiResponse = if (response.isSuccessful) {
                val body = response.body()
                val responseModel: T = gson.fromJson(body, type)
                ApiResponse.Success(responseModel)
            } else {
                val errorMessage = response.errorBody()?.string() ?: response.message()
                ApiResponse.Fail(Throwable(errorMessage))
            }
            ApiResult(apiResponse, responseHeaders, responseCode)
        } catch (genericException: Exception) {
            ApiResult(ApiResponse.Fail(genericException))
        }
    }

    private suspend fun performGetRequest(
        url: String,
        headers: Map<String, String>,
    ) = apiService.get(url = url, headerMap = headers)

    private suspend fun performPostRequest(
        url: String,
        body: Any?,
        headers: Map<String, String>,
    ) = body?.let {
        apiService.post(url = url, body = it, headerMap = headers)
    } ?: apiService.post(headers, url)

    private suspend fun performPutRequest(
        url: String,
        body: Any?,
        headers: Map<String, String>,
    ) = body?.let {
        apiService.put(url = url, body = it, headerMap = headers)
    } ?: apiService.put(url = url, headerMap = headers)

    private suspend fun performDeleteRequest(
        url: String,
        body: Any?,
        headers: Map<String, String>,
    ) = body?.let {
        apiService.delete(url = url, body = it, headerMap = headers)
    } ?: apiService.delete(url = url, headerMap = headers)
}
