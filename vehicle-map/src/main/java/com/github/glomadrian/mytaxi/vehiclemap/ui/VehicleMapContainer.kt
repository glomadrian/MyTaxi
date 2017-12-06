package com.github.glomadrian.mytaxi.vehiclemap.ui

import com.github.glomadrian.mytaxi.corepresentation.ui.MyTaxiActivity

class VehicleMapContainer: MyTaxiActivity() {

    private val vehicleId by lazy { intent.getStringExtra(VEHICLE_ID) }

    companion object {
        private const val VEHICLE_ID = "vehicle.id.key"
    }

    override fun onRequestFragment() = VehiclesMapFragment.newInstance(vehicleId)
}