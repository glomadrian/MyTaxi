package com.github.glomadrian.mytaxi.api.di

import com.github.glomadrian.mytaxi.api.di.internal.myTaxiApiGraph
import com.github.salomonbrys.kodein.Kodein

val apiGraph = Kodein.Module {
    import(myTaxiApiGraph)
}