package com.github.glomadrian.mytaxi.vehiclemap.ui

import android.os.Bundle
import android.widget.Toast
import com.github.glomadrian.mytaxi.corepresentation.di.component.ApplicationComponent
import com.github.glomadrian.mytaxi.corepresentation.ui.MyTaxiFragment
import com.github.glomadrian.mytaxi.vehiclemap.di.DaggerVehicleMapComponent
import com.github.glomadrian.vehicle_map.R
import org.jetbrains.anko.support.v4.withArguments

class VehiclesMapFragment: MyTaxiFragment() {

    private val vehicleId by lazy { arguments?.getString(VEHICLE_ID) }

    companion object {
        private const val VEHICLE_ID = "vehicle.id.key"

        fun newInstance(vehicleId: String) = VehiclesMapFragment().withArguments(
                VEHICLE_ID to vehicleId
        )
    }
    override fun doInjection(applicationComponent: ApplicationComponent) {
        DaggerVehicleMapComponent.builder().applicationComponent(applicationComponent).build().inject(this)
    }

    override fun onRequestLayoutResource() = R.layout.vehicles_map

    override fun onViewReady(savedInstanceState: Bundle?) {
        Toast.makeText(context, vehicleId, Toast.LENGTH_LONG).show()
    }
}