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
            view?.showLoading()
            getAvailableVehicles().await()
                    .onSuccess {
                        view?.hideLoading()
                        view?.renderVehicles(it)
                    }
                    .onFailure {
                        view?.hideLoading()
                        view?.showError()
                    }
        }
    }

    private fun getAvailableVehicles() = bg {
        getAvailableVehiclesUseCase.execute().map { it.map { toView(it) } }
    }

    interface View {
        fun renderVehicles(vehicles: List<ListableVehicleViewModel>)
        fun showLoading()
        fun hideLoading()
        fun showError()
    }
}


