package com.github.glomadrian.mytaxi.vehiclemap.di

import com.github.glomadrian.mytaxi.corepresentation.di.applicationInjector
import com.github.salomonbrys.kodein.Kodein

val vehicleMapInjector = Kodein {
    extend(applicationInjector)
    import(vehicleMapGraph)
}