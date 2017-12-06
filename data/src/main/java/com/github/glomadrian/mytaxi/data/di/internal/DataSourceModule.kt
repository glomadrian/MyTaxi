package com.github.glomadrian.mytaxi.data.di.internal

import com.github.glomadrian.mytaxi.api.mytaxi.MyTaxpiApiClient
import com.github.glomadrian.mytaxi.data.vehicles.datasource.MyTaxiCloudDataSource
import com.github.glomadrian.mytaxi.data.vehicles.datasource.VehicleCloudDataSource
import dagger.Module
import dagger.Provides

@Module
internal class DataSourceModule {

    @Provides
    fun provideVehicleCloudDataSource(myTaxpiApiClient: MyTaxpiApiClient): VehicleCloudDataSource =
            MyTaxiCloudDataSource(myTaxpiApiClient)
}