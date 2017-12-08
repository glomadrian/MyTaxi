package com.github.glomadrian.mytaxi.vehiclelist.di

import com.github.glomadrian.mytaxi.coretesting.di.integrationTestGraph
import com.github.salomonbrys.kodein.Kodein

val injector = Kodein {
    extend(integrationTestGraph)
    import(vehicleListGrahp)
}