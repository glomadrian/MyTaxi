package com.github.glomadrian.mytaxi.vehiclemap.presentation

import com.github.glomadrian.mytaxi.core.MAIN
import com.github.glomadrian.mytaxi.corepresentation.presentation.Presenter
import com.github.glomadrian.mytaxi.domainlogic.usecase.vehiclemap.GetVehicleUseCase
import com.github.glomadrian.mytaxi.vehiclemap.presentation.mapper.mapToVehicleInfo
import com.github.glomadrian.mytaxi.vehiclemap.presentation.model.DriverViewModel
import com.github.glomadrian.mytaxi.vehiclemap.presentation.model.StatusIconViewModel
import com.github.glomadrian.mytaxi.vehiclemap.presentation.model.VehicleInfoViewModel
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class VehicleInfoPresenter @Inject constructor(
        private val getVehicleUseCase: GetVehicleUseCase)
    : Presenter<VehicleInfoPresenter.View>() {

    fun onVehicleIdAvailable(id: String) {
        launch(MAIN) {
            getVehicle(id).await()
                    .onSuccess { view?.renderVehicleInfo(it) }
        }

        view?.renderDriver(DriverViewModel("Adrian",
                "https://avatars2.githubusercontent.com/u/5353046?s=460&v=4"))
    }

    private fun getVehicle(id: String) = bg{
        getVehicleUseCase.execute(id).map { mapToVehicleInfo(it) }
    }

    interface View {
        fun renderDriver(driver: DriverViewModel)
        fun renderVehicleInfo(vehicle: VehicleInfoViewModel)
    }
}