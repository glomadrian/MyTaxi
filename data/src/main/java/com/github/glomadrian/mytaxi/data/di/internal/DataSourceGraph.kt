package com.github.glomadrian.mytaxi.data.di.internal

import com.github.glomadrian.mytaxi.data.vehicles.datasource.HashMapVehicleMemoryDataSource
import com.github.glomadrian.mytaxi.data.vehicles.datasource.MyTaxiCloudDataSource
import com.github.glomadrian.mytaxi.data.vehicles.datasource.VehicleCloudDataSource
import com.github.glomadrian.mytaxi.data.vehicles.datasource.VehicleMemoryDataSource
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider

internal val dataSourceGraph = Kodein.Module {
    bind<VehicleCloudDataSource>() with provider { MyTaxiCloudDataSource(instance()) }
    bind<VehicleMemoryDataSource>() with provider { HashMapVehicleMemoryDataSource() }
}