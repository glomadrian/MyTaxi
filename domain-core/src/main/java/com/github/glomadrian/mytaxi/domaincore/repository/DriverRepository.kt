package com.github.glomadrian.mytaxi.domaincore.repository

import com.github.glomadrian.mytaxi.domaincore.model.Driver
import org.funktionale.tries.Try

interface DriverRepository {
    fun getDriverForVehicle(vehicleId: String): Try<Driver>
}