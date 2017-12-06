package com.github.glomadrian.mytaxi.vehiclemap.presentation

import com.github.glomadrian.mytaxi.core.MAIN
import com.github.glomadrian.mytaxi.corepresentation.presentation.Presenter
import com.github.glomadrian.mytaxi.domainlogic.usecase.vehiclelist.GetAvailableVehiclesUseCase
import com.github.glomadrian.mytaxi.domainlogic.usecase.vehiclemap.GetVehicleUseCase
import com.github.glomadrian.mytaxi.vehiclemap.presentation.mapper.toSelectedLocation
import com.github.glomadrian.mytaxi.vehiclemap.presentation.mapper.toVehicleLocation
import com.github.glomadrian.mytaxi.vehiclemap.presentation.model.SelectedLocationViewModel
import com.github.glomadrian.mytaxi.vehiclemap.presentation.model.VehicleLocationViewModel
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class VehicleMapPresenter @Inject constructor(
        private val getAvailableVehiclesUseCase: GetAvailableVehiclesUseCase,
        private val getVehicleUseCase: GetVehicleUseCase) : Presenter<VehicleMapPresenter.View>() {

    fun onVehicleId(id: String) {
        launch(MAIN) {
            getVehiclesLocation().await()
                    .onSuccess { view?.renderVehicleLocations(it) }
        }
        launch(MAIN) {
            getVehicle(id).await().onSuccess { view?.renderSelectedLocation(it) }
        }
    }

    private fun getVehiclesLocation() = bg {
        getAvailableVehiclesUseCase.execute().map { it.map { toVehicleLocation(it) } }
    }

    private fun getVehicle(id: String) = bg {
        getVehicleUseCase.execute(id).map { toSelectedLocation(it)  }
    }

    interface View {
        fun renderSelectedLocation(selectedLocationViewModel: SelectedLocationViewModel)
        fun renderVehicleLocations(locations: List<VehicleLocationViewModel>)
    }
}