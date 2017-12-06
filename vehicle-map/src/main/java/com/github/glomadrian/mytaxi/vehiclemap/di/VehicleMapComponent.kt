package com.github.glomadrian.mytaxi.vehiclemap.di

import com.github.glomadrian.mytaxi.core.di.scopes.PerView
import com.github.glomadrian.mytaxi.corepresentation.di.component.ApplicationComponent
import com.github.glomadrian.mytaxi.vehiclemap.ui.VehiclesMapFragment
import com.github.glomadrian.mytaxi.vehiclemap.ui.vehicleinfo.VehicleInfoFragment
import dagger.Component

@PerView
@Component(dependencies = [(ApplicationComponent::class)])
interface VehicleMapComponent {

    fun inject(vehiclesMapFragment: VehiclesMapFragment)
    fun inject(vehicleInfoFragment: VehicleInfoFragment)
}