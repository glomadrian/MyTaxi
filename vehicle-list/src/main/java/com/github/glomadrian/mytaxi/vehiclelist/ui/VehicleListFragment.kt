package com.github.glomadrian.mytaxi.vehiclelist.ui

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.github.glomadrian.mytaxi.corepresentation.ui.MyTaxiFragment
import com.github.glomadrian.mytaxi.vehiclelist.R
import com.github.glomadrian.mytaxi.vehiclelist.di.vehicleListInjector
import com.github.glomadrian.mytaxi.vehiclelist.presentation.VehicleListPresenter
import com.github.glomadrian.mytaxi.vehiclelist.presentation.model.ListableVehicleViewModel
import com.github.glomadrian.mytaxi.vehiclelist.ui.adapter.VehicleAdapter
import com.github.salomonbrys.kodein.instance
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.vehicle_list.*

class VehicleListFragment: MyTaxiFragment(), VehicleListPresenter.View {

    private val presenter: VehicleListPresenter = vehicleListInjector.instance()
    private val vehicleAdapter by lazy { VehicleAdapter() }

    companion object {
        fun newInstance() = VehicleListFragment()
    }

    override fun onRequestLayoutResource() = R.layout.vehicle_list

    override fun onViewReady(savedInstanceState: Bundle?) {
        initializeToolbar()
        initializeList()
        presenter.onViewReady()
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

    private fun initializeList(){
        vehicleList.layoutManager = LinearLayoutManager(context)
        vehicleList.adapter = vehicleAdapter
    }

    override fun renderVehicles(vehicles: List<ListableVehicleViewModel>) {
        vehicleAdapter.addVehicles(vehicles)
    }

    override fun onAttachPresenter() {
        presenter.onAttach(this)
    }

    override fun onDetachPresenter() {
        presenter.onDetach()
    }

    override fun showLoading() {
        loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loading.visibility = View.GONE
    }

    override fun showError() {
        view?.let {
            Snackbar.make(it, R.string.generic_error, Snackbar.LENGTH_SHORT)
        }
    }
}