package com.github.glomadrian.mytaxi.data.vehicles.datasource

import com.github.glomadrian.mytaxi.core.di.extensions.toTry
import com.github.glomadrian.mytaxi.core.exception.CoreException
import com.github.glomadrian.mytaxi.domaincore.model.Vehicle
import org.funktionale.tries.Try

internal class HashMapVechicleMemoryDataSource : VehicleMemoryDataSource {
    private val vehiclesMap = mutableMapOf<String, Vehicle>()

    override fun store(vehicles: List<Vehicle>) {
        vehicles.forEach {
            vehiclesMap.put(it.id, it)
        }
    }

    override fun getVehicles(): Try<List<Vehicle>> = {
        if (vehiclesMap.isNotEmpty()) {
            vehiclesMap.values.toList()
        } else {
            throw CoreException.NotFound()
        }
    }.toTry()


    override fun getVehicle(id: String) = {
        vehiclesMap.let {
            if (it.containsKey(id)) {
                it[id]!!
            } else {
                throw CoreException.NotFound()
            }
        }
    }.toTry()

    override fun clear() {
        vehiclesMap.clear()
    }

}