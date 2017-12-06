package com.github.glomadrian.mytaxi.vehiclemap.presentation

import com.github.glomadrian.mytaxi.core.MAIN
import com.github.glomadrian.mytaxi.corepresentation.presentation.Presenter
import com.github.glomadrian.mytaxi.domainlogic.usecase.vehiclelist.GetAvailableVehiclesUseCase
import com.github.glomadrian.mytaxi.vehiclemap.presentation.mapper.toVehicleLocation
import com.github.glomadrian.mytaxi.vehiclemap.presentation.model.VehicleLocationViewModel
import kotlinx.coroutines.experimental.launch
import org.funktionale.tries.Try
import javax.inject.Inject

class VehicleMapPresenter @Inject constructor(
        private val getAvailableVehiclesUseCase: GetAvailableVehiclesUseCase
) : Presenter<VehicleMapPresenter.View>() {

    fun onVehicleId(vehicleId: String) {
        launch(MAIN) {
            val result = getVehiclesLocation().await()
                    .onSuccess {
                        view?.renderVehicleLocations(it)
                    }

            getSelectedVehicle(result, vehicleId).await().onSuccess {
                it?.let { view?.renderSelectedLocation(it) }
            }
        }
    }

    private fun getVehiclesLocation() = bg {
        getAvailableVehiclesUseCase.execute().map { it.map { toVehicleLocation(it) } }
    }

    private fun getSelectedVehicle(vehicles: Try<List<VehicleLocationViewModel>>, vehicleId: String) = bg {
        vehicles.map { it.find { it.id == vehicleId } }
    }

    interface View {
        fun renderSelectedLocation(selectedVehicle: VehicleLocationViewModel)
        fun renderVehicleLocations(locations: List<VehicleLocationViewModel>)
    }
}