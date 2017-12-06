package com.github.glomadrian.mytaxi.vehiclelist.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.glomadrian.mytaxi.corepresentation.di.component.ApplicationComponent
import com.github.glomadrian.mytaxi.corepresentation.ui.MyTaxiRenderer
import com.github.glomadrian.mytaxi.vehiclelist.R
import com.github.glomadrian.mytaxi.vehiclelist.di.DaggerVehicleListComponent
import com.github.glomadrian.mytaxi.vehiclelist.presentation.model.ListableVehicleViewModel
import kotlinx.android.synthetic.main.vehicle_view.view.*

class VehicleRenderer(prent: ViewGroup) : MyTaxiRenderer<ListableVehicleViewModel>(prent) {

    override fun doInjection(applicationComponent: ApplicationComponent) {
        val component = DaggerVehicleListComponent.builder().applicationComponent(applicationComponent).build()
        component.inject(this)
    }

    override fun provideView(viewGroup: ViewGroup, layoutInflater: LayoutInflater) =
            layoutInflater.inflate(R.layout.vehicle_view, viewGroup, false)

    override fun render(viewModel: ListableVehicleViewModel) {
        view.direction.text = viewModel.direction
        view.icon.setImageResource(viewModel.icon.iconResource)
        view.vehicleId.text = viewModel.name
    }
}