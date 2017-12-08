package com.github.glomadrian.mytaxi.domainlogic.usecase.di.internal

import com.github.glomadrian.mytaxi.domainlogic.usecase.vehiclemap.GetDriverByVehicleIdUseCase
import com.github.glomadrian.mytaxi.domainlogic.usecase.vehiclemap.GetVehicleUseCase
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider

internal val vehicleMapUseCaseGraph = Kodein.Module {
    bind<GetDriverByVehicleIdUseCase>() with provider { GetDriverByVehicleIdUseCase(instance()) }
    bind<GetVehicleUseCase>() with provider { GetVehicleUseCase(instance()) }
}