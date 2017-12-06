package com.github.glomadrian.mytaxi.api.di

import com.github.glomadrian.mytaxi.api.di.internal.MyTaxiApiModule
import dagger.Module

@Module(includes = [MyTaxiApiModule::class])
class ApiModule {

}