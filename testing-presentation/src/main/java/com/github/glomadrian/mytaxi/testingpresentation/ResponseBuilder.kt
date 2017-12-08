package com.github.glomadrian.mytaxi.testingpresentation

import java.net.HttpURLConnection
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.SocketPolicy

class ResponseBuilder @JvmOverloads constructor(code: Int = HttpURLConnection.HTTP_OK) {
    private val response: MockResponse = MockResponse()

    companion object {
        private val STRING_ENCODING = "UTF-8"
    }

    init {
        response.setResponseCode(code)
    }

    fun with200Ok(): ResponseBuilder {
        response.setResponseCode(HttpURLConnection.HTTP_OK)
        return this
    }

    fun with404NotFound(): ResponseBuilder {
        response.setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)
        return this
    }

    fun with500InternalServerError(): ResponseBuilder {
        response.setResponseCode(HttpURLConnection.HTTP_INTERNAL_ERROR)
        return this
    }

    fun with204NoContent(): ResponseBuilder {
        response.setResponseCode(HttpURLConnection.HTTP_NO_CONTENT)
        return this
    }

    fun with400BadRequest(): ResponseBuilder {
        response.setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST)
        return this
    }

    fun with409Conflict(): ResponseBuilder {
        response.setResponseCode(HttpURLConnection.HTTP_CONFLICT)
        return this
    }

    fun withNetworkError(): ResponseBuilder {
        response.socketPolicy = SocketPolicy.DISCONNECT_AT_START
        return this
    }

    fun withBody(body: String): ResponseBuilder {
        response.setBody(body)
        return this
    }

    fun build(): MockResponse {
        return response
    }
}

