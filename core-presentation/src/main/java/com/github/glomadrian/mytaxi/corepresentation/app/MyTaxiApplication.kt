package com.github.glomadrian.mytaxi.corepresentation.app

import android.app.Application
import com.github.glomadrian.mytaxi.core.BACKGROUND
import com.github.glomadrian.mytaxi.core.MAIN
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.newFixedThreadPoolContext

class MyTaxiApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeThreading()
    }

    private fun initializeThreading() {
        MAIN = UI
        BACKGROUND = newFixedThreadPoolContext(2 * Runtime.getRuntime().availableProcessors(), "bg")
    }
}