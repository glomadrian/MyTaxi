package com.github.glomadrian.mytaxi.vehiclemap.presentation.mapper

import com.github.glomadrian.mytaxi.domaincore.model.Vehicle
import com.github.glomadrian.mytaxi.vehiclemap.presentation.model.VehicleLocationViewModel

fun toVehicleLocation(source: Vehicle) = VehicleLocationViewModel(
        source.id,
        source.location.latitude,
        source.location.longitude)