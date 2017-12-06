package com.github.glomadrian.mytaxi.vehiclemap.presentation.mapper

import com.github.glomadrian.mytaxi.domaincore.model.Vehicle
import com.github.glomadrian.mytaxi.vehiclemap.presentation.model.SelectedLocationViewModel

fun toSelectedLocation(source: Vehicle) = SelectedLocationViewModel(
        source.location.latitude,
        source.location.longitude)
