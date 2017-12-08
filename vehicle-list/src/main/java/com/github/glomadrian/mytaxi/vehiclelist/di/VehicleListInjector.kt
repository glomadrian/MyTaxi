package com.github.glomadrian.mytaxi.vehiclelist.di

import com.github.glomadrian.mytaxi.corepresentation.di.applicationInjector
import com.github.salomonbrys.kodein.Kodein

val vehicleListInjector = Kodein{
    extend(applicationInjector)
    import(vehicleListGraph)
}