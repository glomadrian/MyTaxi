package com.github.glomadrian.mytaxi.vehiclelist.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.glomadrian.mytaxi.vehiclelist.R

class LeftVehicleRenderer(parent: ViewGroup): VehicleRenderer(parent) {

    override fun provideView(viewGroup: ViewGroup, layoutInflater: LayoutInflater) =
            layoutInflater.inflate(R.layout.vehicle_view, viewGroup, false)
}