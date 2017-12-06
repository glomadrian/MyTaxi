package com.github.glomadrian.mytaxi.domaincore.model

data class Vehicle(
        val id: String,
        val name: String,
        val address: String,
        val location: GeoLocation,
        val interiorStatus: Status,
        val exteriorStatus: Status,
        val fuelPercent: Int,
        val engine: Engine)