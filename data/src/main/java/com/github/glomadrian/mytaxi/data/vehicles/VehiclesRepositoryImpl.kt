package com.github.glomadrian.mytaxi.data.vehicles

import com.github.glomadrian.mytaxi.data.vehicles.datasource.VehicleCloudDataSource
import com.github.glomadrian.mytaxi.domaincore.repository.VehicleRepository

internal class VehiclesRepositoryImpl constructor(
        private val vehicleCloudDataSource: VehicleCloudDataSource) : VehicleRepository {

    override fun getAvailableVehicles() = vehicleCloudDataSource.getVehicles()

}