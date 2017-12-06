package com.github.glomadrian.mytaxi.vehiclelist.presentation

import com.github.glomadrian.mytaxi.corepresentation.presentation.Presenter
import com.github.glomadrian.mytaxi.vehiclelist.presentation.model.ListableVehicleViewModel
import com.github.glomadrian.mytaxi.vehiclelist.presentation.model.VehicleIconViewModel
import javax.inject.Inject

class VehicleListPresenter @Inject constructor(): Presenter<VehicleListPresenter.View>() {

    fun onViewReady() {
        //Mocked for a while
        view?.renderVehicles( listOf(
                ListableVehicleViewModel("Direccion1","a123Xasd1", VehicleIconViewModel.TAXI_ONE),
                ListableVehicleViewModel("Direccion1","a123Xasd1", VehicleIconViewModel.TAXI_TWO),
                ListableVehicleViewModel("Direccion1","a123Xasd1", VehicleIconViewModel.TAXI_THREE),
                ListableVehicleViewModel("Direccion1","a123Xasd1", VehicleIconViewModel.TAXI_ONE),
                ListableVehicleViewModel("Direccion1","a123Xasd1", VehicleIconViewModel.TAXI_TWO),
                ListableVehicleViewModel("Direccion1","a123Xasd1", VehicleIconViewModel.TAXI_THREE),
                ListableVehicleViewModel("Direccion1","a123Xasd1", VehicleIconViewModel.TAXI_ONE),
                ListableVehicleViewModel("Direccion1","a123Xasd1", VehicleIconViewModel.TAXI_TWO),
                ListableVehicleViewModel("Direccion1","a123Xasd1", VehicleIconViewModel.TAXI_THREE)
        ))

    }

    interface View {
        fun renderVehicles(vehicles: List<ListableVehicleViewModel>)
    }
}


