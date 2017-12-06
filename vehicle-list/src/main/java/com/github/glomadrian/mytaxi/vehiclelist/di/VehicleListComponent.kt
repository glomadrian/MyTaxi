package com.github.glomadrian.mytaxi.vehiclelist.di

import com.github.glomadrian.mytaxi.core.di.scopes.PerView
import com.github.glomadrian.mytaxi.corepresentation.di.component.ApplicationComponent
import com.github.glomadrian.mytaxi.vehiclelist.ui.VehicleListFragment
import com.github.glomadrian.mytaxi.vehiclelist.ui.adapter.VehicleRenderer
import dagger.Component

@PerView
@Component(dependencies = [(ApplicationComponent::class)])
interface VehicleListComponent{

    fun inject(vehicleList: VehicleListFragment)
    fun inject(vehicleRenderer: VehicleRenderer)
}