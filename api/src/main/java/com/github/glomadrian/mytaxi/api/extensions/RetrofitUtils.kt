package com.github.glomadrian.mytaxi.api.extensions

import com.github.glomadrian.mytaxi.core.exception.CoreException
import retrofit2.Response
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException

internal inline fun <T> responseExceptionCaptor(f: () -> Response<T>): T {
    try {
        val response = f.invoke()
        if (!response.isSuccessful) {
            throw when (response.code()) {
                HttpURLConnection.HTTP_NOT_FOUND -> CoreException.NotFound()
                else ->  CoreException.Unknown()
            }
        }
        val body = response.body()
        if (body == null) {
            throw CoreException.NotFound()
        } else {
            return body
        }
    } catch (socketTimeoutException: SocketTimeoutException) {
        throw CoreException.Timeout()
    } catch (unknownHostException: UnknownHostException) {
        throw CoreException.UnknownHost()
    } catch (exception: Exception) {
        throw CoreException.Unknown()
    }
}

