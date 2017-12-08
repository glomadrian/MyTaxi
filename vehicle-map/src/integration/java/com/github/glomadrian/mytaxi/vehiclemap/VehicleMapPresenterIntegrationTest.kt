package com.github.glomadrian.mytaxi.vehiclemap

import com.github.glomadrian.mytaxi.testingpresentation.IntegrationTest
import com.github.glomadrian.mytaxi.testingpresentation.responses.mytaxiapi.enqueueValidVehicleResponse
import com.github.glomadrian.mytaxi.vehiclemap.di.injector
import com.github.glomadrian.mytaxi.vehiclemap.presentation.VehicleMapPresenter
import com.github.glomadrian.mytaxi.vehiclemap.presentation.model.VehicleLocationViewModel
import com.github.salomonbrys.kodein.instance
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.verify
import junit.framework.Assert.assertEquals
import okhttp3.mockwebserver.MockWebServer
import org.junit.Test
import org.mockito.Mock

class VehicleMapPresenterIntegrationTest : IntegrationTest() {

    companion object {
        private const val VEHICLE_ID = "WME4513341K565439"
        private const val VALID_LATITUDE = 53.59301
        private const val VALID_LONGITUDE = 10.07526
    }

    @Mock lateinit var view: VehicleMapPresenter.View
    private val presenter: VehicleMapPresenter = injector.instance()
    private val mockWebServer: MockWebServer = injector.instance()

    @Test
    fun shouldRenderAListOfVehiclePositions() {
        enqueueValidVehicleResponse(mockWebServer)
        presenter.onAttach(view)

        presenter.onVehicleId(VEHICLE_ID)

        verify(view).renderVehicleLocations(any())
    }

    @Test
    fun shouldRenderASelectedVehicle() {
        enqueueValidVehicleResponse(mockWebServer)
        presenter.onAttach(view)

        presenter.onVehicleId(VEHICLE_ID)

        verify(view).renderSelectedLocation(any())
    }

    @Test
    fun shouldRenderTheVehicleWithSameRequestedId() {
        enqueueValidVehicleResponse(mockWebServer)
        presenter.onAttach(view)
        val argumentCaptor = argumentCaptor<VehicleLocationViewModel>()

        presenter.onVehicleId(VEHICLE_ID)

        verify(view).renderSelectedLocation(argumentCaptor.capture())
        argumentCaptor.lastValue.apply{
            assertEquals(id, VEHICLE_ID)
        }
    }

    @Test
    fun shouldRenderSelectedVehicleWithValidCoordinates(){
        enqueueValidVehicleResponse(mockWebServer)
        presenter.onAttach(view)
        val argumentCaptor = argumentCaptor<VehicleLocationViewModel>()

        presenter.onVehicleId(VEHICLE_ID)

        verify(view).renderSelectedLocation(argumentCaptor.capture())
        argumentCaptor.lastValue.apply{
            assertEquals(latitude, VALID_LATITUDE)
            assertEquals(longitude, VALID_LONGITUDE)
        }
    }

}