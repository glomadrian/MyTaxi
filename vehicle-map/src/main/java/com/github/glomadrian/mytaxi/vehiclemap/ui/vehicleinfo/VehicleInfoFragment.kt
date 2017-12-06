package com.github.glomadrian.mytaxi.vehiclemap.ui.vehicleinfo

import android.os.Bundle
import com.github.glomadrian.mytaxi.corepresentation.di.component.ApplicationComponent
import com.github.glomadrian.mytaxi.corepresentation.ui.MyTaxiFragment
import com.github.glomadrian.mytaxi.vehiclemap.R
import com.github.glomadrian.mytaxi.vehiclemap.di.DaggerVehicleMapComponent
import org.jetbrains.anko.support.v4.withArguments

class VehicleInfoFragment : MyTaxiFragment() {

    companion object {
        private const val VEHICLE_ID = "vehicle.id.key"

        fun newInstance(vehicleId: String) = VehicleInfoFragment().withArguments(
                VEHICLE_ID to vehicleId
        )
    }

    override fun doInjection(applicationComponent: ApplicationComponent) {
        DaggerVehicleMapComponent.builder().applicationComponent(applicationComponent).build().inject(this)
    }

    override fun onRequestLayoutResource() = R.layout.vehicle_info

    override fun onViewReady(savedInstanceState: Bundle?) {

    }

}