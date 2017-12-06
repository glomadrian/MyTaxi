package com.github.glomadrian.mytaxi.api.mytaxi.retrofit

import com.github.glomadrian.mytaxi.api.extensions.responseExceptionCaptor
import com.github.glomadrian.mytaxi.api.mytaxi.MyTaxpiApiClient
import com.github.glomadrian.mytaxi.api.mytaxi.model.VehicleApiModel

internal class MyTaxiRetrofitApiClient(private val retrofitService: MyTaxiRetrofitService) : MyTaxpiApiClient {

    override fun getVehicles(): List<VehicleApiModel> = responseExceptionCaptor {
        retrofitService.getVehicles().execute()
    }.placemarks
}