package com.github.glomadrian.mytaxi.vehiclelist.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.glomadrian.mytaxi.corepresentation.di.component.ApplicationComponent
import com.github.glomadrian.mytaxi.corepresentation.ui.MyTaxiFragment
import com.github.glomadrian.mytaxi.vehiclelist.R
import com.github.glomadrian.mytaxi.vehiclelist.di.DaggerVehicleListComponent
import kotlinx.android.synthetic.main.toolbar.*

class VehicleListFragment: MyTaxiFragment() {

    companion object {
        fun newInstance() = VehicleListFragment()
    }

    override fun doInjection(applicationComponent: ApplicationComponent) {
        val component = DaggerVehicleListComponent.builder().applicationComponent(applicationComponent).build()
        component.inject(this)
    }

    override fun onRequestLayoutResource() = R.layout.vehicle_list

    override fun onViewReady(savedInstanceState: Bundle?) {
        initializeToolbar()
    }

    private fun initializeToolbar() {
        val activity = activity
        if (activity is AppCompatActivity) {
            activity.setSupportActionBar(toolbar)
            activity.supportActionBar?.apply {
                setDisplayShowTitleEnabled(false)
            }
        }
    }

}