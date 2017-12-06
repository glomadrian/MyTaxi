package com.github.glomadrian.mytaxi.corepresentation.app

import android.app.Application
import com.github.glomadrian.mytaxi.core.BACKGROUND
import com.github.glomadrian.mytaxi.core.MAIN
import com.github.glomadrian.mytaxi.corepresentation.di.component.DaggerApplicationComponent
import com.github.glomadrian.mytaxi.corepresentation.di.module.ApplicationModule
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.newFixedThreadPoolContext

class MyTaxiApplication : Application() {

    val applicationComponent by lazy {
        DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this)).build()
    }

    override fun onCreate() {
        super.onCreate()
        initializeThreading()
        inject()
    }

    private fun initializeThreading() {
        MAIN = UI
        BACKGROUND = newFixedThreadPoolContext(2 * Runtime.getRuntime().availableProcessors(), "bg")
    }

    private fun inject() {
        applicationComponent!!.inject(this)
    }
}