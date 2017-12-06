package com.github.glomadrian.mytaxi.data.vehicles

import com.github.glomadrian.mytaxi.data.vehicles.datasource.VehicleCloudDataSource
import com.github.glomadrian.mytaxi.data.vehicles.datasource.VehicleMemoryDataSource
import com.github.glomadrian.mytaxi.domaincore.repository.VehicleRepository

internal class VehiclesRepositoryImpl constructor(
        private val vehicleCloudDataSource: VehicleCloudDataSource,
        private val vehicleMemoryDataSource: VehicleMemoryDataSource) : VehicleRepository {

    override fun getAvailableVehicles() = vehicleCloudDataSource.getVehicles().onSuccess {
        vehicleMemoryDataSource.store(it)
    }

    override fun getVehicle(id: String) = vehicleMemoryDataSource.getVehicle(id)
}