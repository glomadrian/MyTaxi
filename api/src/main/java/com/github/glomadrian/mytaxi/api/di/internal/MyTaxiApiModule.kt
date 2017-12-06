package com.github.glomadrian.mytaxi.api.di.internal

import com.github.glomadrian.mytaxi.api.mytaxi.MyTaxpiApiClient
import com.github.glomadrian.mytaxi.api.mytaxi.retrofit.MyTaxiRetrofitApiClient
import com.github.glomadrian.mytaxi.api.mytaxi.retrofit.MyTaxiRetrofitService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
internal class MyTaxiApiModule {

    companion object {
        private const val MY_TAXI_API_URL = "http://redirect.mytaxi.net"
    }

    @Provides
    fun provideMyTaxiApi(): MyTaxpiApiClient {
        val reotfit = Retrofit.Builder().baseUrl(MY_TAXI_API_URL)
                .client(createHTPPClient())
                .addConverterFactory(createGsonConverter())
                .build()
        val service = reotfit.create(MyTaxiRetrofitService::class.java)
        return MyTaxiRetrofitApiClient(service)
    }

    private fun createHTPPClient() =  OkHttpClient.Builder().build()

    private fun createGsonConverter(): GsonConverterFactory {
        val gson = GsonBuilder().create()
        return GsonConverterFactory.create(gson)
    }
}