package com.github.glomadrian.mytaxi.vehiclemap.ui

import android.content.Context
import com.github.glomadrian.mytaxi.vehiclemap.R
import com.github.glomadrian.mytaxi.vehiclemap.extensions.mapIconFromSvgRes
import com.github.glomadrian.mytaxi.vehiclemap.presentation.model.VehicleLocationViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer

class TaxiMarkerRenderer(private val context: Context, map: GoogleMap, clusterManager: ClusterManager<VehicleLocationViewModel>) :
        DefaultClusterRenderer<VehicleLocationViewModel>(context, map, clusterManager) {

    override fun onBeforeClusterItemRendered(item: VehicleLocationViewModel?, markerOptions: MarkerOptions?) {
        markerOptions?.icon(mapIconFromSvgRes(R.drawable.ic_marker, context))
    }

}