package com.github.glomadrian.mytaxi.vehiclelist.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.github.glomadrian.mytaxi.vehiclelist.presentation.model.ListableVehicleViewModel

class VehicleAdapter : RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder>() {
    private val vehiclesViewModels by lazy { mutableListOf<ListableVehicleViewModel>() }

    override fun getItemCount() = vehiclesViewModels.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VehicleViewHolder(VehicleRenderer(parent))

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        holder.render(vehiclesViewModels[position])
    }

    class VehicleViewHolder(val vehicleRenderer: VehicleRenderer) : RecyclerView.ViewHolder(vehicleRenderer.view) {
        fun render(vehicle: ListableVehicleViewModel) {
            vehicleRenderer.render(vehicle)
        }
    }

    fun addVehicles(vehicles: List<ListableVehicleViewModel>) {
        vehiclesViewModels.addAll(vehicles)
        notifyDataSetChanged()
    }
}