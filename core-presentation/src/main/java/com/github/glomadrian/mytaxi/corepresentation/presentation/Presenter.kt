package com.github.glomadrian.mytaxi.corepresentation.presentation

import com.github.glomadrian.mytaxi.core.BACKGROUND
import com.github.glomadrian.mytaxi.core.MAIN
import kotlinx.coroutines.experimental.*
import kotlin.coroutines.experimental.CoroutineContext

abstract class Presenter<T> {

    protected var view: T? = null
    private val presenterJob = Job()

    fun onAttach(view: T) {
        this.view = view
    }

    fun onDetach() {
        view = null
        presenterJob.cancel()
    }

    protected fun <T> bg(
            context: CoroutineContext = BACKGROUND,
            block: suspend CoroutineScope.() -> T)
            : Deferred<T> = async(presenterJob + context) {
        block()
    }

    protected fun main(context: CoroutineContext = MAIN,
                       block: suspend CoroutineScope.() -> T): Job =
            launch(presenterJob + context) {
                block
            }
}
