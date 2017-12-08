package com.github.glomadrian.mytaxi.domainlogic.usecase.vehiclemap

import com.github.glomadrian.mytaxi.domaincore.repository.VehicleRepository
import javax.inject.Inject

class GetVehicleUseCase constructor(private val vehicleRepository: VehicleRepository) {

    fun execute(id: String) = vehicleRepository.getVehicle(id)
}