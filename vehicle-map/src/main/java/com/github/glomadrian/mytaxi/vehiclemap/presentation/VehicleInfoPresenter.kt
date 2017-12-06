package com.github.glomadrian.mytaxi.vehiclemap.presentation

import com.github.glomadrian.mytaxi.corepresentation.presentation.Presenter
import com.github.glomadrian.mytaxi.vehiclemap.presentation.model.DriverViewModel
import com.github.glomadrian.mytaxi.vehiclemap.presentation.model.StatusIcon
import com.github.glomadrian.mytaxi.vehiclemap.presentation.model.VehicleInfoViewModel
import javax.inject.Inject

class VehicleInfoPresenter @Inject constructor(): Presenter<VehicleInfoPresenter.View>() {

    fun onVehicleIdAvailable(id: String) {
        //Moc
        view?.renderDriver(DriverViewModel("Adrian",
                "https://avatars2.githubusercontent.com/u/5353046?s=460&v=4"))
        view?.renderVehicleInfo(VehicleInfoViewModel("1",
                "xFg4Jam112333",
                60,
                200,
                "fake streen 123, 123",
                StatusIcon.BAD,
                StatusIcon.REALLY_GOOD))
    }

    interface View {
        fun renderDriver(driver: DriverViewModel)
        fun renderVehicleInfo(vehicle: VehicleInfoViewModel)
    }
}