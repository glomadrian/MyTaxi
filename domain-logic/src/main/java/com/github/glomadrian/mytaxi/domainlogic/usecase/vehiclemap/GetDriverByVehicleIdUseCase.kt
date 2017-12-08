package com.github.glomadrian.mytaxi.domainlogic.usecase.vehiclemap

import com.github.glomadrian.mytaxi.domaincore.repository.DriverRepository
import javax.inject.Inject

class GetDriverByVehicleIdUseCase constructor(private val driverRepository: DriverRepository){

    fun execute(vehicleId: String) = driverRepository.getDriverForVehicle(vehicleId)
}