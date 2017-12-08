package com.github.glomadrian.mytaxi.vehiclelist.di

import com.github.glomadrian.mytaxi.vehiclelist.presentation.VehicleListPresenter
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider

val vehicleListGrahp = Kodein.Module{
    bind<VehicleListPresenter>() with provider { VehicleListPresenter(instance()) }
}