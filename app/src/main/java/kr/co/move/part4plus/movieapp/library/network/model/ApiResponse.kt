package kr.co.move.part4plus.movieapp.library.network.model

import com.google.gson.annotations.SerializedName

sealed class ApiResponse<out T> {
    class Success<T>(
        @SerializedName("data") val data: T
    ) : ApiResponse<T>()

    class Fail(
        @SerializedName("error") val error: Throwable
    ) : ApiResponse<Nothing>()
}
