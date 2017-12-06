package com.github.glomadrian.mytaxi.vehiclemap.presentation.model

import android.support.annotation.DrawableRes
import com.github.glomadrian.mytaxi.vehiclemap.R

enum class StatusIconViewModel(@DrawableRes val iconResource: Int) {
    REALLY_GOOD(R.drawable.ic_really_god),
    GOOD(R.drawable.ic_good),
    BAD(R.drawable.ic_bad),
    UNACCEPTABLE(R.drawable.ic_unacceptable)
}