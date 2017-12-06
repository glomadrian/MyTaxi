package com.github.glomadrian.mytaxi.api.mytaxi.retrofit

import com.github.glomadrian.mytaxi.api.mytaxi.model.VehicleResponseApiModel
import retrofit2.Call
import retrofit2.http.GET

interface MyTaxiRetrofitService {
    @GET("car2go/vehicles.json")
    fun getVehicles(): Call<VehicleResponseApiModel>
}