package com.github.glomadrian.mytaxi.vehiclemap.di

import com.github.glomadrian.mytaxi.vehiclemap.presentation.VehicleInfoPresenter
import com.github.glomadrian.mytaxi.vehiclemap.presentation.VehicleMapPresenter
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider

val vehicleMapGraph = Kodein.Module {
    bind<VehicleInfoPresenter>() with provider { VehicleInfoPresenter(instance(), instance()) }
    bind<VehicleMapPresenter>() with provider { VehicleMapPresenter(instance()) }
}