package kr.co.move.part4plus.movieapp.library.network.model

class NetworkRequestInfo private constructor(
    val method: RequestType,
    val parameters: String?,
    val headers: Map<String, String>,
    val body: Any?,
) {

    class Builder(private val method: RequestType = RequestType.GET) {

        private val headers: MutableMap<String, String> = mutableMapOf()
        private var parameters: String? = null
        private var body: Any? = null

        fun withQueryParameters(queryParameters: String) = apply {
            parameters = queryParameters
        }

        fun withHeaders(headerMap: Map<String, String>) = apply {
            headers.putAll(headerMap)
        }

        fun withBody(requestBody: Any) = apply {
            body = requestBody
        }

        fun build(): NetworkRequestInfo {
            return NetworkRequestInfo(method, parameters, headers, body)
        }
    }
}
