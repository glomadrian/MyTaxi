package com.github.glomadrian.mytaxi.core.exception

sealed class CoreException : Exception() {
    class NotFound : CoreException()
    class Network : CoreException()
    class Unknown : CoreException()
    class UnknownHost : CoreException()
    class Timeout : CoreException()
}