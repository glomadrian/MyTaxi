package com.github.glomadrian.mytaxi.vehiclelist.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.glomadrian.mytaxi.corepresentation.di.component.ApplicationComponent
import com.github.glomadrian.mytaxi.corepresentation.navigator.Navigator
import com.github.glomadrian.mytaxi.corepresentation.navigator.vehiclesMapNavigationCommand
import com.github.glomadrian.mytaxi.corepresentation.ui.MyTaxiRenderer
import com.github.glomadrian.mytaxi.vehiclelist.R
import com.github.glomadrian.mytaxi.vehiclelist.di.DaggerVehicleListComponent
import com.github.glomadrian.mytaxi.vehiclelist.presentation.model.ListableVehicleViewModel
import kotlinx.android.synthetic.main.vehicle_view.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import javax.inject.Inject

class VehicleRenderer(prent: ViewGroup) : MyTaxiRenderer<ListableVehicleViewModel>(prent) {

    @Inject lateinit var navigator: Navigator

    override fun doInjection(applicationComponent: ApplicationComponent) {
        val component = DaggerVehicleListComponent.builder().applicationComponent(applicationComponent).build()
        component.inject(this)
    }

    override fun provideView(viewGroup: ViewGroup, layoutInflater: LayoutInflater) =
            layoutInflater.inflate(R.layout.vehicle_view, viewGroup, false)

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