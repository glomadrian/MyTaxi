package com.github.glomadrian.mytaxi.data.di.internal

import com.github.glomadrian.mytaxi.data.drivers.MockDriverRepository
import com.github.glomadrian.mytaxi.data.vehicles.VehiclesRepositoryImpl
import com.github.glomadrian.mytaxi.domaincore.repository.DriverRepository
import com.github.glomadrian.mytaxi.domaincore.repository.VehicleRepository
import com.github.salomonbrys.kodein.*

internal val repositoryGraph = Kodein.Module {
    bind<VehicleRepository>() with singleton { VehiclesRepositoryImpl(instance(), instance()) }
    bind<DriverRepository>() with singleton { MockDriverRepository() }
}