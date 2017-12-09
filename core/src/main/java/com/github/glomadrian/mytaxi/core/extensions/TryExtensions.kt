package com.github.glomadrian.mytaxi.core.extensions

import org.funktionale.tries.Try

private typealias function<T> = () -> T

fun <T> function<T>.toTry(): Try<T> {
    return try {
        Try.Success(this())
    } catch (exception: Exception) {
        Try.Failure(exception)
    }
}