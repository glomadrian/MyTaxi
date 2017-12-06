package com.github.glomadrian.mytaxi.domainlogic.usecase.vehiclelist

import com.github.glomadrian.mytaxi.domaincore.repository.VehicleRepository
import javax.inject.Inject

class GetAvailableVehiclesUseCase @Inject constructor(
        private val vehicleRepository: VehicleRepository) {

    fun execute() = vehicleRepository.getAvailableVehicles()
}