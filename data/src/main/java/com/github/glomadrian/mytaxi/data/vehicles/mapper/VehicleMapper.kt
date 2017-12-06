package com.github.glomadrian.mytaxi.data.vehicles.mapper

import com.github.glomadrian.mytaxi.api.mytaxi.model.VehicleApiModel
import com.github.glomadrian.mytaxi.domaincore.model.Engine
import com.github.glomadrian.mytaxi.domaincore.model.GeoLocation
import com.github.glomadrian.mytaxi.domaincore.model.Status
import com.github.glomadrian.mytaxi.domaincore.model.Vehicle

internal fun toDomain(source: VehicleApiModel) = Vehicle(
        source.vin,
        source.name,
        source.address,
        mapLocation(source.coordinates),
        mapStatus(source.interior),
        mapStatus(source.exterior),
        source.fuel,
        mapEngine(source.engineType)
)

private fun mapLocation(location: List<Double>) = GeoLocation(location[1], location[0])

private fun mapStatus(status: VehicleApiModel.State) =
    when (status) {
        VehicleApiModel.State.REALLY_GOOD -> Status.REALLY_GOOD
        VehicleApiModel.State.GOOD -> Status.GOOD
        VehicleApiModel.State.BAD -> Status.BAD
        VehicleApiModel.State.UNACCEPTABLE -> Status.UNACCEPTABLE
    }

private fun mapEngine(engine: VehicleApiModel.EngineType) =
    when (engine) {
        VehicleApiModel.EngineType.CE -> Engine.COMBUSTION
    }

