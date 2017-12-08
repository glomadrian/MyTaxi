package com.github.glomadrian.mytaxi.vehiclelist.ui.adapter

import android.view.ViewGroup
import com.github.glomadrian.mytaxi.corepresentation.navigator.Navigator
import com.github.glomadrian.mytaxi.corepresentation.navigator.vehiclesMapNavigationCommand
import com.github.glomadrian.mytaxi.corepresentation.ui.MyTaxiRenderer
import com.github.glomadrian.mytaxi.vehiclelist.di.vehicleListInjector
import com.github.glomadrian.mytaxi.vehiclelist.presentation.model.ListableVehicleViewModel
import com.github.salomonbrys.kodein.instance
import kotlinx.android.synthetic.main.vehicle_view.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick

abstract class VehicleRenderer(parent: ViewGroup) : MyTaxiRenderer<ListableVehicleViewModel>(parent) {

    private val navigator: Navigator = vehicleListInjector.instance()

    override fun render(viewModel: ListableVehicleViewModel) {
        initializeListeners(viewModel)
        view.direction.text = viewModel.direction
        view.icon.setImageResource(viewModel.icon.iconResource)
        view.vehicleId.text = viewModel.name
    }

    private fun initializeListeners(viewModel: ListableVehicleViewModel) {
        view.vehicleContainer.onClick {
            navigator.navigate(view.context, vehiclesMapNavigationCommand(viewModel.id))
        }
    }
}