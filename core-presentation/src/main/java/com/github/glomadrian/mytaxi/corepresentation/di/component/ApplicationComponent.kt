package com.github.glomadrian.mytaxi.corepresentation.di.component

import com.github.glomadrian.mytaxi.api.di.ApiModule
import com.github.glomadrian.mytaxi.core.di.scopes.PerApp
import com.github.glomadrian.mytaxi.corepresentation.app.MyTaxiApplication
import com.github.glomadrian.mytaxi.corepresentation.di.module.ApplicationModule
import com.github.glomadrian.mytaxi.data.di.DataModule
import com.github.glomadrian.mytaxi.domaincore.repository.VehicleRepository
import dagger.Component

@PerApp
@Component(modules = [(ApplicationModule::class), (DataModule::class), (ApiModule::class)])
interface ApplicationComponent {

    fun inject(myTaxiApplication: MyTaxiApplication)

    fun getMyTaxiApplication(): MyTaxiApplication
    fun getVehicleRepository(): VehicleRepository
}
