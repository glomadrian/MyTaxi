package com.github.glomadrian.mytaxi.coretesting

import kotlinx.coroutines.experimental.CoroutineDispatcher
import kotlin.coroutines.experimental.CoroutineContext

object TestContext : CoroutineDispatcher() {
    override fun isDispatchNeeded(context: CoroutineContext): Boolean = false
    override fun dispatch(context: CoroutineContext, block: Runnable) { block.run() }
    override fun toString(): String = "Current"
}