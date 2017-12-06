package com.github.glomadrian.mytaxi.api.mytaxi

import com.github.glomadrian.mytaxi.api.mytaxi.model.VehicleApiModel

interface MyTaxpiApiClient {

    fun getVehicles(): List<VehicleApiModel>
}