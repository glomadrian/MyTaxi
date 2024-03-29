package com.github.glomadrian.mytaxi.vehiclemap.ui

import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.AnimationUtils
import com.github.glomadrian.mytaxi.corepresentation.extensions.replaceAndCommit
import com.github.glomadrian.mytaxi.corepresentation.ui.MyTaxiFragment
import com.github.glomadrian.mytaxi.vehiclemap.R
import com.github.glomadrian.mytaxi.vehiclemap.di.vehicleMapInjector
import com.github.glomadrian.mytaxi.vehiclemap.extensions.mapIconFromSvgRes
import com.github.glomadrian.mytaxi.vehiclemap.presentation.VehicleMapPresenter
import com.github.glomadrian.mytaxi.vehiclemap.presentation.model.VehicleLocationViewModel
import com.github.salomonbrys.kodein.instance
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.vehicles_map.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.withArguments

class VehiclesMapFragment : MyTaxiFragment(), VehicleMapPresenter.View {

    private val presenter: VehicleMapPresenter = vehicleMapInjector.instance()
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<*>
    private val vehicleId by lazy { arguments?.getString(VEHICLE_ID) }
    private val mapFragment by lazy { childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment }
    private lateinit var googleMap: GoogleMap
    private lateinit var clusterManager: ClusterManager<VehicleLocationViewModel>
    private val markerIcon by lazy { context?.let { mapIconFromSvgRes(R.drawable.ic_marker, it) } }

    companion object {
        private const val MAP_ZOOM = 16.toFloat()
        private const val VEHICLE_ID = "vehicle.id.key"
        private const val CAMERA_MOV_TIME = 600

        fun newInstance(vehicleId: String) = VehiclesMapFragment().withArguments(
                VEHICLE_ID to vehicleId
        )
    }

    override fun onRequestLayoutResource() = R.layout.vehicles_map

    override fun onViewReady(savedInstanceState: Bundle?) {
        bottomSheetBehavior = BottomSheetBehavior.from(vehicleInfoContainer)
        setupBottomSheetBehaviour()
        initMap()
        updateVehicleView()
        initListeners()
        initializeToolbar()
        renderInfoAnimation()
    }

    private fun initializeToolbar() {
        val activity = activity
        if (activity is AppCompatActivity) {
            activity.setSupportActionBar(toolbar)
            activity.supportActionBar?.apply {
                setDisplayShowHomeEnabled(true)
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowTitleEnabled(false)
            }
        }
        toolbar.setNavigationOnClickListener { activity?.finish() }
    }

    private fun renderInfoAnimation() {
        val anim = AnimationUtils.loadAnimation(context, R.anim.bounce)
        vehicleInfoContainer.animation = anim
        anim.start()
    }

    private fun initPresenter() {
        vehicleId?.let {
            presenter.onVehicleId(it)
        } ?: activity?.finish()
    }

    private fun initMap() {
        mapFragment.getMapAsync { googleMap ->
            this.googleMap = googleMap
            clusterManager = ClusterManager(context, googleMap)
            googleMap.setOnCameraIdleListener(clusterManager)
            googleMap.setOnMarkerClickListener(clusterManager)
            context?.let {
                clusterManager.renderer = TaxiMarkerRenderer(it, googleMap, clusterManager)
            }
            initPresenter()
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
        clusterManager.addItems(locations)
    }

    override fun renderSelectedLocation(selectedVehicle: VehicleLocationViewModel) {
        googleMap.addMarker(MarkerOptions()
                .position(LatLng(selectedVehicle.latitude, selectedVehicle.longitude))
                .visible(true)
                .icon(markerIcon)
                .title(selectedVehicle.name))
                .showInfoWindow()
        moveCameraToSelectedVehicle(selectedVehicle)
    }

    private fun moveCameraToSelectedVehicle(selectedVehicle: VehicleLocationViewModel) {
        selectedVehicle.apply {
            val cameraPosition = CameraPosition.builder()
                    .target(LatLng(latitude, longitude))
                    .zoom(MAP_ZOOM)
                    .build()
            CameraUpdateFactory.newCameraPosition(cameraPosition)
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), CAMERA_MOV_TIME, null)
        }
    }

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