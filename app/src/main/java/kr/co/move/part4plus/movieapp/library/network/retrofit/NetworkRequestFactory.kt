package kr.co.move.part4plus.movieapp.library.network.retrofit

import kr.co.move.part4plus.movieapp.library.network.model.ApiResult
import kr.co.move.part4plus.movieapp.library.network.model.NetworkRequestInfo
import java.lang.reflect.Type

interface NetworkRequestFactory {

    suspend fun <T> create(
        url: String,
        requestInfo: NetworkRequestInfo = NetworkRequestInfo.Builder().build(),
        type: Type
    ): ApiResult<T>
}
