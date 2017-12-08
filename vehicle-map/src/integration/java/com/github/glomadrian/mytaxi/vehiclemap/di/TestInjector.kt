package com.github.glomadrian.mytaxi.vehiclemap.di

import com.github.glomadrian.mytaxi.coretesting.di.integrationTestGraph
import com.github.glomadrian.mytaxi.vehiclemap.di.vehicleMapGraph
import com.github.glomadrian.mytaxi.vehiclemap.di.vehicleMapInjector
import com.github.salomonbrys.kodein.Kodein

val injector = Kodein {
    extend(integrationTestGraph)
    import(vehicleMapGraph)
}