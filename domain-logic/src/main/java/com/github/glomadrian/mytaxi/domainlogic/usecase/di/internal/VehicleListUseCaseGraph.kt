package com.github.glomadrian.mytaxi.domainlogic.usecase.di.internal

import com.github.glomadrian.mytaxi.domainlogic.usecase.vehiclelist.GetAvailableVehiclesUseCase
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider

internal val vehicleListUseCaseGraph = Kodein.Module {
    bind<GetAvailableVehiclesUseCase>() with provider { GetAvailableVehiclesUseCase(instance()) }
}