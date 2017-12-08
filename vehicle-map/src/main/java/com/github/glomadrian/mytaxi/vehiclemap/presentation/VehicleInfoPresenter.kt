package com.github.glomadrian.mytaxi.vehiclemap.presentation

import com.github.glomadrian.mytaxi.core.MAIN
import com.github.glomadrian.mytaxi.corepresentation.presentation.Presenter
import com.github.glomadrian.mytaxi.domainlogic.usecase.vehiclemap.GetDriverByVehicleIdUseCase
import com.github.glomadrian.mytaxi.domainlogic.usecase.vehiclemap.GetVehicleUseCase
import com.github.glomadrian.mytaxi.vehiclemap.presentation.mapper.mapToVehicleInfo
import com.github.glomadrian.mytaxi.vehiclemap.presentation.mapper.toDriverViewModel
import com.github.glomadrian.mytaxi.vehiclemap.presentation.model.DriverViewModel
import com.github.glomadrian.mytaxi.vehiclemap.presentation.model.VehicleInfoViewModel
import kotlinx.coroutines.experimental.launch

class VehicleInfoPresenter constructor(
        private val getVehicleUseCase: GetVehicleUseCase,
        private val getDriverByVehicleIdUseCase: GetDriverByVehicleIdUseCase)
    : Presenter<VehicleInfoPresenter.View>() {

    fun onVehicleIdAvailable(id: String) {
        launch(MAIN) {
            getVehicle(id).await()
                    .onSuccess { view?.renderVehicleInfo(it) }
            getDriver(id).await()
                    .onSuccess { view?.renderDriver(it) }
        }
    }

    private fun getVehicle(id: String) = bg {
        getVehicleUseCase.execute(id).map { mapToVehicleInfo(it) }
    }

    private fun getDriver(vehicleId: String) = bg {
        getDriverByVehicleIdUseCase.execute(vehicleId).map { toDriverViewModel(it) }
    }

    interface View {
        fun renderDriver(driver: DriverViewModel)
        fun renderVehicleInfo(vehicle: VehicleInfoViewModel)
    }
}