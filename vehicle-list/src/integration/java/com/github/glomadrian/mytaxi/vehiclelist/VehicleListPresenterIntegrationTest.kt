package com.github.glomadrian.mytaxi.vehiclelist

import com.github.glomadrian.mytaxi.testingpresentation.IntegrationTest
import com.github.glomadrian.mytaxi.testingpresentation.responses.mytaxiapi.enqueueValidVehicleResponse
import com.github.glomadrian.mytaxi.vehiclelist.di.injector
import com.github.glomadrian.mytaxi.vehiclelist.presentation.VehicleListPresenter
import com.github.salomonbrys.kodein.instance
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import okhttp3.mockwebserver.MockWebServer
import org.junit.Test
import org.mockito.Mock

class VehicleListPresenterIntegrationTest : IntegrationTest() {

    @Mock lateinit var view: VehicleListPresenter.View
    private val presenter: VehicleListPresenter = injector.instance()
    private val mockWebServer: MockWebServer = injector.instance()

    @Test
    fun shouldRenderVehiclesWhenPresenterIsReady() {
        enqueueValidVehicleResponse(mockWebServer)
        presenter.onAttach(view)

        presenter.onViewReady()

        verify(view).renderVehicles(any())
    }
}