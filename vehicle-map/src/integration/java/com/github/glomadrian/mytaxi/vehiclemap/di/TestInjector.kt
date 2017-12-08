package com.github.glomadrian.mytaxi.vehiclemap.di

import com.github.glomadrian.mytaxi.testingpresentation.di.integrationTestGraph
import com.github.salomonbrys.kodein.Kodein

val injector = Kodein {
    extend(integrationTestGraph)
    import(vehicleMapGraph)
}