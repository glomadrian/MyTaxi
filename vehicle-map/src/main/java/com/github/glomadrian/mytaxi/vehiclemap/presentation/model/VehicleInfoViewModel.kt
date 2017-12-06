package com.github.glomadrian.mytaxi.vehiclemap.presentation.model

data class VehicleInfoViewModel(
        val id: String,
        val name: String,
        val fuelPercent: Int,
        val distanceInMeters: Int,
        val direction: String,
        val interior: StatusIcon,
        val exterior: StatusIcon
        )