package com.github.glomadrian.mytaxi.api.di.internal

import com.github.glomadrian.mytaxi.api.mytaxi.MyTaxpiApiClient
import com.github.glomadrian.mytaxi.api.mytaxi.retrofit.MyTaxiRetrofitApiClient
import com.github.glomadrian.mytaxi.api.mytaxi.retrofit.MyTaxiRetrofitService
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.provider
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val MY_TAXI_API_URL = "http://redirect.mytaxi.net"

internal val myTaxiApiGraph = Kodein.Module {
    bind<MyTaxpiApiClient>() with provider {
        val reotfit = Retrofit.Builder().baseUrl(MY_TAXI_API_URL)
                .client(createHTPPClient())
                .addConverterFactory(createGsonConverter())
                .build()
        val service = reotfit.create(MyTaxiRetrofitService::class.java)
        MyTaxiRetrofitApiClient(service)
    }
}

private fun createHTPPClient() =  OkHttpClient.Builder().build()

private fun createGsonConverter(): GsonConverterFactory {
    val gson = GsonBuilder().create()
    return GsonConverterFactory.create(gson)
}