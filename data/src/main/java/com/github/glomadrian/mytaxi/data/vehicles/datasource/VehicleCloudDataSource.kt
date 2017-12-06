package com.github.glomadrian.mytaxi.data.vehicles.datasource

import com.github.glomadrian.mytaxi.domaincore.model.Vehicle
import org.funktionale.tries.Try

internal interface VehicleCloudDataSource {
    fun getVehicles(): Try<List<Vehicle>>
}