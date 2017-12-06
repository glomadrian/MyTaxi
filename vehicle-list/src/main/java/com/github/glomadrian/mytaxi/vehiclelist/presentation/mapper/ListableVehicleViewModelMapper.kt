package com.github.glomadrian.mytaxi.vehiclelist.presentation.mapper

import com.github.glomadrian.mytaxi.domaincore.model.Vehicle
import com.github.glomadrian.mytaxi.vehiclelist.presentation.model.ListableVehicleViewModel
import com.github.glomadrian.mytaxi.vehiclelist.presentation.model.VehicleIconViewModel
import java.util.*

fun toView(source: Vehicle) = ListableVehicleViewModel(
        source.address,
        source.id,
        source.name,
        generateRandomIcon())

private fun generateRandomIcon(): VehicleIconViewModel =
        VehicleIconViewModel.values().let {
            it[Random().nextInt(it.size)]
        }