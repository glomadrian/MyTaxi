package com.github.glomadrian.mytaxi.data.di.internal

import com.github.glomadrian.mytaxi.core.di.scopes.PerApp
import com.github.glomadrian.mytaxi.data.drivers.MockDriverRepository
import com.github.glomadrian.mytaxi.data.vehicles.VehiclesRepositoryImpl
import com.github.glomadrian.mytaxi.data.vehicles.datasource.VehicleCloudDataSource
import com.github.glomadrian.mytaxi.data.vehicles.datasource.VehicleMemoryDataSource
import com.github.glomadrian.mytaxi.domaincore.repository.DriverRepository
import com.github.glomadrian.mytaxi.domaincore.repository.VehicleRepository
import dagger.Module
import dagger.Provides

@Module
internal class RepositoryModule {

    @PerApp
    @Provides
    fun provideVehiclesRepository(vehicleCloudDataSource: VehicleCloudDataSource,
                                  vehicleMemoryDataSource: VehicleMemoryDataSource): VehicleRepository =
            VehiclesRepositoryImpl(vehicleCloudDataSource, vehicleMemoryDataSource)

    @PerApp
    @Provides
    fun provideDriverRepository(): DriverRepository = MockDriverRepository()

}