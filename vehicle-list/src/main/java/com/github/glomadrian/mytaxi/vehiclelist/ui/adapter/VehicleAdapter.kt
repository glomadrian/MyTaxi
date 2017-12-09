package com.github.glomadrian.mytaxi.vehiclelist.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.github.glomadrian.mytaxi.vehiclelist.presentation.model.ListableVehicleViewModel

class VehicleAdapter : RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder>() {

    private val vehiclesViewModels by lazy { mutableListOf<ListableVehicleViewModel>() }

    companion object {
        private const val LEFT_VIEW = 0
        private const val RIGHT_VIEW = 1
    }

    override fun getItemCount() = vehiclesViewModels.size

    override fun getItemViewType(position: Int) =
            if (position % 2 == 0) {
                LEFT_VIEW
            } else {
                RIGHT_VIEW
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        LEFT_VIEW -> VehicleViewHolder(LeftVehicleRenderer(parent))
        RIGHT_VIEW -> VehicleViewHolder(RightVehicleRenderer(parent))
        else -> VehicleViewHolder(LeftVehicleRenderer(parent))
    }

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