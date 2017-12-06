package com.github.glomadrian.mytaxi.vehiclelist.ui

import com.github.glomadrian.mytaxi.corepresentation.ui.MyTaxiActivity

class VehicleListContainer: MyTaxiActivity() {

    override fun onRequestFragment() = VehicleListFragment.newInstance()
}