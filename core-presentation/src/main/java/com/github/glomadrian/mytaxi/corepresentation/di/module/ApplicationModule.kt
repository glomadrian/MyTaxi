package com.github.glomadrian.mytaxi.corepresentation.di.module

import com.github.glomadrian.mytaxi.core.di.scopes.PerApp
import com.github.glomadrian.mytaxi.corepresentation.app.MyTaxiApplication
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val application: MyTaxiApplication) {

    @PerApp
    @Provides
    fun provide(): MyTaxiApplication {
        return application
    }
}
