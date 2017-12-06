package com.github.glomadrian.mytaxi.vehiclemap.ui

import android.animation.ValueAnimator
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.view.View
import com.github.glomadrian.mytaxi.corepresentation.di.component.ApplicationComponent
import com.github.glomadrian.mytaxi.corepresentation.ui.MyTaxiFragment
import com.github.glomadrian.mytaxi.vehiclemap.R
import com.github.glomadrian.mytaxi.vehiclemap.di.DaggerVehicleMapComponent
import kotlinx.android.synthetic.main.vehicles_map.*
import org.jetbrains.anko.support.v4.withArguments

class VehiclesMapFragment: MyTaxiFragment() {
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<*>
    private val vehicleId by lazy { arguments?.getString(VEHICLE_ID) }

    companion object {
        private const val VEHICLE_ID = "vehicle.id.key"

        fun newInstance(vehicleId: String) = VehiclesMapFragment().withArguments(
                VEHICLE_ID to vehicleId
        )
    }

    override fun doInjection(applicationComponent: ApplicationComponent) {
        DaggerVehicleMapComponent.builder().applicationComponent(applicationComponent).build().inject(this)
    }

    override fun onRequestLayoutResource() = R.layout.vehicles_map

    override fun onViewReady(savedInstanceState: Bundle?) {
        bottomSheetBehavior = BottomSheetBehavior.from(vehicleInfoContainer)
        setupBottomSheetBehaviour()
    }

    private fun setupBottomSheetBehaviour() {
        bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {}

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                val value = (slideOffset * 100).toInt()
                placeActionButtonAccordingCurve()
                vehicleInfoContainer.setCurvePercent(value)
            }
        })
    }

    private fun placeActionButtonAccordingCurve() {
        orderAction.translationY = vehicleInfoContainer.curvedMiddlePoint.y
    }


}