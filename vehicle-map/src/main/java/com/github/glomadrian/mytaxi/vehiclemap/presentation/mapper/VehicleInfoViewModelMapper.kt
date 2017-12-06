package com.github.glomadrian.mytaxi.vehiclemap.presentation.mapper

import com.github.glomadrian.mytaxi.domaincore.model.Status
import com.github.glomadrian.mytaxi.domaincore.model.Vehicle
import com.github.glomadrian.mytaxi.vehiclemap.presentation.model.StatusIconViewModel
import com.github.glomadrian.mytaxi.vehiclemap.presentation.model.VehicleInfoViewModel
import java.util.*

fun mapToVehicleInfo(source: Vehicle) = VehicleInfoViewModel(
        source.id,
        source.name,
        source.fuelPercent,
        getRandomDisatnce(),
        source.address,
        mapStatus(source.interiorStatus),
        mapStatus(source.exteriorStatus)
)

private fun mapStatus(source: Status) =
        when (source) {
            Status.REALLY_GOOD -> StatusIconViewModel.REALLY_GOOD
            Status.GOOD -> StatusIconViewModel.GOOD
            Status.BAD -> StatusIconViewModel.BAD
            Status.UNACCEPTABLE -> StatusIconViewModel.UNACCEPTABLE
        }


private fun getRandomDisatnce() = Random().nextInt(500)