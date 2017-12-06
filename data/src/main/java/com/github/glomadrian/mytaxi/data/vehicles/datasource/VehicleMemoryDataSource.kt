package com.github.glomadrian.mytaxi.data.vehicles.datasource

import com.github.glomadrian.mytaxi.domaincore.model.Vehicle
import org.funktionale.tries.Try

internal interface VehicleMemoryDataSource {
    fun store(vehicles: List<Vehicle>)
    fun getVehicles(): Try<List<Vehicle>>
    fun getVehicle(id: String): Try<Vehicle>
    fun clear()
}