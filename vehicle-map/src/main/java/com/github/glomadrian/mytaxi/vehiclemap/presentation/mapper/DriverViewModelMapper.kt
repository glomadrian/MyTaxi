package com.github.glomadrian.mytaxi.vehiclemap.presentation.mapper

import com.github.glomadrian.mytaxi.domaincore.model.Driver
import com.github.glomadrian.mytaxi.vehiclemap.presentation.model.DriverViewModel

fun toDriverViewModel(source: Driver) = DriverViewModel(source.name, source.photoUrl)