package com.github.glomadrian.mytaxi.data.vehicles.datasource

import com.github.glomadrian.mytaxi.api.mytaxi.MyTaxpiApiClient
import com.github.glomadrian.mytaxi.core.di.extensions.toTry
import com.github.glomadrian.mytaxi.data.vehicles.mapper.toDomain

class MyTaxiCloudDataSource constructor(
        private val myTaxiApiClient: MyTaxpiApiClient) : VehicleCloudDataSource {

    override fun getVehicles() = { myTaxiApiClient.getVehicles().map {
        toDomain(it)
    } }.toTry()

}