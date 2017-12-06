package com.github.glomadrian.mytaxi.data.di

import com.github.glomadrian.mytaxi.data.di.internal.DataSourceModule
import com.github.glomadrian.mytaxi.data.di.internal.RepositoryModule
import dagger.Module

@Module(includes = [(RepositoryModule::class), (DataSourceModule::class)])
class DataModule {

}