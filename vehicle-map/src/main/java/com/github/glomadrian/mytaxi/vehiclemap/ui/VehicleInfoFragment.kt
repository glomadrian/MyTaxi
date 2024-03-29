package com.github.glomadrian.mytaxi.vehiclemap.ui

import android.os.Bundle
import com.github.glomadrian.mytaxi.corepresentation.ui.MyTaxiFragment
import com.github.glomadrian.mytaxi.vehiclemap.R
import com.github.glomadrian.mytaxi.vehiclemap.di.vehicleMapInjector
import com.github.glomadrian.mytaxi.vehiclemap.presentation.VehicleInfoPresenter
import com.github.glomadrian.mytaxi.vehiclemap.presentation.model.DriverViewModel
import com.github.glomadrian.mytaxi.vehiclemap.presentation.model.VehicleInfoViewModel
import com.github.glomadrian.mytaxi.vehiclemap.ui.picasso.CircleTransform
import com.github.salomonbrys.kodein.instance
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.vehicle_info.*
import org.jetbrains.anko.support.v4.withArguments
import javax.inject.Inject

class VehicleInfoFragment : MyTaxiFragment(), VehicleInfoPresenter.View {

    private val presenter: VehicleInfoPresenter = vehicleMapInjector.instance()
    private val vehicleId by lazy { arguments?.getString(VehicleInfoFragment.VEHICLE_ID) }

    companion object {
        private const val VEHICLE_ID = "vehicle.id.key"
        private const val ANIMATION_TIMME = 4000.toLong()

        fun newInstance(vehicleId: String) = VehicleInfoFragment().withArguments(
                VEHICLE_ID to vehicleId
        )
    }

    override fun onRequestLayoutResource() = R.layout.vehicle_info

    override fun onViewReady(savedInstanceState: Bundle?) {
        vehicleId?.let {
            presenter.onVehicleIdAvailable(it)
        }
    }

    override fun renderDriver(driver: DriverViewModel) {
        driverName.text = driver.name
        Picasso.with(context).load(driver.photoUrl).transform(CircleTransform()).into(driverPhoto)
    }

    override fun renderVehicleInfo(vehicle: VehicleInfoViewModel) {
        distanceValue.text = getString(R.string.distanceString, vehicle.distanceInMeters.toString())
        directionValue.text = vehicle.direction
        fuelChart.setFirstValuePercent(vehicle.fuelPercent)
        fuelChart.setSecondValuePercent(0)
        fuelChart.startAnimation(ANIMATION_TIMME)
        interiorState.setImageResource(vehicle.interior.iconResource)
        exteriorState.setImageResource(vehicle.exterior.iconResource)
        name.text = vehicle.name
    }

    override fun onAttachPresenter() {
        presenter.onAttach(this)
    }

    override fun onDetachPresenter() {
        presenter.onDetach()
    }
}