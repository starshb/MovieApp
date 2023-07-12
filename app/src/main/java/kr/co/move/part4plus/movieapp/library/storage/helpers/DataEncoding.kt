package kr.co.move.part4plus.movieapp.library.storage.helpers

import java.util.Base64
import javax.inject.Inject

class DataEncoding @Inject constructor() {

    fun encodeToString(data: ByteArray?): String? {
        return Base64.getEncoder().encodeToString(data)
    }

    fun decode(data: String?): ByteArray? {
        return Base64.getDecoder().decode(data)
    }
}
