package com.github.glomadrian.mytaxi.vehiclemap.ui

import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.view.View
import com.github.glomadrian.mytaxi.corepresentation.di.component.ApplicationComponent
import com.github.glomadrian.mytaxi.corepresentation.extensions.replaceAndCommit
import com.github.glomadrian.mytaxi.corepresentation.ui.MyTaxiFragment
import com.github.glomadrian.mytaxi.vehiclemap.R
import com.github.glomadrian.mytaxi.vehiclemap.di.DaggerVehicleMapComponent
import com.github.glomadrian.mytaxi.vehiclemap.presentation.VehicleMapPresenter
import com.github.glomadrian.mytaxi.vehiclemap.presentation.model.VehicleLocationViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.vehicles_map.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.withArguments
import javax.inject.Inject

class VehiclesMapFragment : MyTaxiFragment(), VehicleMapPresenter.View {
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<*>
    private val vehicleId by lazy { arguments?.getString(VEHICLE_ID) }
    @Inject lateinit var presenter: VehicleMapPresenter
    private val mapFragment by lazy { childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment }
    private lateinit var googleMap: GoogleMap

    companion object {
        private const val MAP_ZOOM = 16.toFloat()
        private const val MAP_OFFSET_Y = 60.toFloat()
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
        initMap()
        initListeners()
    }

    private fun initPresenter() {
        vehicleId?.let {
            presenter.onVehicleId(it)
        } ?: activity?.finish()
    }

    private fun initMap() {
        mapFragment.getMapAsync { googleMap ->
            this.googleMap = googleMap
            initPresenter()
            updateVehicleView()
        }
    }

    private fun initListeners() {
        orderAction.onClick {
            TaxiOrderedModalFragment.newInstance().show(childFragmentManager, TaxiOrderedModalFragment::class.java.name)
        }
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

    override fun renderVehicleLocations(locations: List<VehicleLocationViewModel>) {
        locations.forEach {
            addMarkerForVehicle(it)
        }
    }

    override fun renderSelectedLocation(selectedVehicle: VehicleLocationViewModel) {
        addMarkerForVehicle(selectedVehicle).showInfoWindow()
        selectedVehicle.apply {
            val cameraPosition = CameraPosition.builder()
                    .target(LatLng(latitude, longitude))
                    .zoom(MAP_ZOOM)
                    .build()
            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
            googleMap.moveCamera(CameraUpdateFactory.scrollBy(0.toFloat(), MAP_OFFSET_Y))
        }
    }

    private fun addMarkerForVehicle(vehicleLocation: VehicleLocationViewModel) =
            googleMap.addMarker(MarkerOptions()
                    .position(LatLng(vehicleLocation.latitude, vehicleLocation.longitude))
                    .visible(true)
                    .title(vehicleLocation.name)
            )

    private fun updateVehicleView() {
        vehicleId?.let {
            childFragmentManager.replaceAndCommit(R.id.infoContainer, VehicleInfoFragment.newInstance(it))
        }
    }

    override fun onAttachPresenter() {
        presenter.onAttach(this)
    }

    override fun onDetachPresenter() {
        presenter.onDetach()
    }
}