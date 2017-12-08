package com.github.glomadrian.mytaxi.vehiclelist.presentation

import com.github.glomadrian.mytaxi.core.MAIN
import com.github.glomadrian.mytaxi.corepresentation.presentation.Presenter
import com.github.glomadrian.mytaxi.domainlogic.usecase.vehiclelist.GetAvailableVehiclesUseCase
import com.github.glomadrian.mytaxi.vehiclelist.presentation.mapper.toView
import com.github.glomadrian.mytaxi.vehiclelist.presentation.model.ListableVehicleViewModel
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class VehicleListPresenter constructor(
        private val getAvailableVehiclesUseCase: GetAvailableVehiclesUseCase)
    : Presenter<VehicleListPresenter.View>() {

    fun onViewReady() {
        launch(MAIN) {
            getAvailableVehicles().await()
                    .onSuccess { view?.renderVehicles(it) }
                    .onFailure { it.printStackTrace() }
        }
    }

    private fun getAvailableVehicles() = bg {
        getAvailableVehiclesUseCase.execute().map { it.map { toView(it) } }
    }

    interface View {
        fun renderVehicles(vehicles: List<ListableVehicleViewModel>)
    }
}


