package com.github.glomadrian.mytaxi.domainlogic.usecase.di

import com.github.glomadrian.mytaxi.domainlogic.usecase.di.internal.vehicleListUseCaseGraph
import com.github.glomadrian.mytaxi.domainlogic.usecase.di.internal.vehicleMapUseCaseGraph
import com.github.salomonbrys.kodein.Kodein

val useCaseGraph = Kodein.Module {
    import(vehicleListUseCaseGraph)
    import(vehicleMapUseCaseGraph)
}