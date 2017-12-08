package com.github.glomadrian.mytaxi.vehiclemap.presentation.model

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

data class VehicleLocationViewModel(val id: String, val latitude: Double, val longitude: Double, val name: String) : ClusterItem {
    override fun getSnippet() = name

    override fun getTitle() = name

    override fun getPosition() = LatLng(latitude, longitude)

}