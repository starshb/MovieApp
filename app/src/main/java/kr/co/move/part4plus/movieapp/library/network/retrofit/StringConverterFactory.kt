package kr.co.move.part4plus.movieapp.library.network.retrofit

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import okio.Buffer
import retrofit2.Converter
import retrofit2.Retrofit
import java.io.OutputStreamWriter
import java.io.Writer
import java.lang.reflect.Type
import java.nio.charset.Charset

class StringConverterFactory(private val gson: Gson) : Converter.Factory() {

    companion object {

        private val MEDIA_TYPE = "application/json; charset=UTF-8".toMediaTypeOrNull()
        private val UTF_8 = Charset.forName("UTF-8")
    }

    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<out Annotation>,
        methodAnnotations: Array<out Annotation>,
        retrofit: Retrofit,
    ): RequestBodyConverter<*> {
        val adapter = gson.getAdapter(TypeToken.get(type))
        return RequestBodyConverter(gson, adapter)
    }

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit,
    ): Converter<ResponseBody, *>? {
        return if (String::class.java == type) {
            ResponseBodyConverter()
        } else {
            super.responseBodyConverter(type, annotations, retrofit)
        }
    }

    inner class RequestBodyConverter<T>(
        private val gson: Gson,
        private val adapter: TypeAdapter<T>,
    ) : Converter<T, RequestBody?> {

        override fun convert(value: T): RequestBody {
            val buffer = Buffer()
            val writer: Writer =
                OutputStreamWriter(buffer.outputStream(), UTF_8)
            val jsonWriter = gson.newJsonWriter(writer)
            adapter.write(jsonWriter, value)
            jsonWriter.close()
            return buffer.readByteString().toRequestBody(MEDIA_TYPE)
        }
    }

    inner class ResponseBodyConverter : Converter<ResponseBody, String> {

        override fun convert(value: ResponseBody): String {
            return value.string()
        }
    }
}
