package com.github.glomadrian.mytaxi.domaincore.repository

import com.github.glomadrian.mytaxi.domaincore.model.Vehicle
import org.funktionale.tries.Try

interface VehicleRepository {
    fun getAvailableVehicles() : Try<List<Vehicle>>
    fun getVehicle(id: String): Try<Vehicle>
}