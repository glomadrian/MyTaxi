package com.github.glomadrian.mytaxi.vehiclelist.presentation.model

import android.support.annotation.DrawableRes
import com.github.glomadrian.mytaxi.vehiclelist.R

enum class VehicleIconViewModel(@DrawableRes val iconResource: Int) {
    TAXI_ONE(R.drawable.ic_taxi_one),
    TAXI_TWO(R.drawable.ic_taxi_two),
    TAXI_THREE(R.drawable.ic_taxi_three)
}