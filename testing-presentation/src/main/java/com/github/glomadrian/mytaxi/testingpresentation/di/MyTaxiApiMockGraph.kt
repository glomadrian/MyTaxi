package com.github.glomadrian.mytaxi.testingpresentation.di

import com.github.glomadrian.mytaxi.api.mytaxi.MyTaxpiApiClient
import com.github.glomadrian.mytaxi.api.mytaxi.retrofit.MyTaxiRetrofitApiClient
import com.github.glomadrian.mytaxi.api.mytaxi.retrofit.MyTaxiRetrofitService
import com.github.salomonbrys.kodein.*
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val PATH = "/"

val apiMockTestGraph = Kodein.Module {
    val httpClient by lazy { OkHttpClient.Builder().build() }
    val gsonConverterFactory by lazy { GsonConverterFactory.create() }

    bind<MyTaxpiApiClient>() with provider {
        val mockwebserver: MockWebServer = instance()
        val reotfit = Retrofit.Builder().baseUrl(mockwebserver.url(PATH))
                .client(httpClient)
                .addConverterFactory(gsonConverterFactory)
                .build()
        val service = reotfit.create(MyTaxiRetrofitService::class.java)
        MyTaxiRetrofitApiClient(service)
    }

    bind<MockWebServer>() with singleton { MockWebServer() }
}