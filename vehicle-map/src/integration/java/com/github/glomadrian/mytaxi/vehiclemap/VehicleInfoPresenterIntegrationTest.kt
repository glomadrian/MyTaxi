package com.github.glomadrian.mytaxi.vehiclemap

import com.github.glomadrian.mytaxi.testingpresentation.IntegrationTest
import com.github.glomadrian.mytaxi.data.vehicles.datasource.VehicleMemoryDataSource
import com.github.glomadrian.mytaxi.domaincore.model.Engine
import com.github.glomadrian.mytaxi.domaincore.model.GeoLocation
import com.github.glomadrian.mytaxi.domaincore.model.Status
import com.github.glomadrian.mytaxi.domaincore.model.Vehicle
import com.github.glomadrian.mytaxi.vehiclemap.di.injector
import com.github.glomadrian.mytaxi.vehiclemap.presentation.VehicleInfoPresenter
import com.github.salomonbrys.kodein.*
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.funktionale.tries.Try
import org.junit.Test
import org.mockito.Mock

class VehicleInfoPresenterIntegrationTest : IntegrationTest() {

    val customInjector = Kodein {
        extend(injector)
        bind<VehicleMemoryDataSource>(overrides = true) with singleton {
            val mockDataSource: VehicleMemoryDataSource = mock()
            mockDataSource
        }
    }

    companion object {
        private const val VEHICLE_ID = "WME4513341K565439"
        private const val FAKE = "fake"
    }

    @Mock lateinit var view: VehicleInfoPresenter.View
    private val presenter: VehicleInfoPresenter = customInjector.instance()
    private val vehicleMemoryDataSource: VehicleMemoryDataSource = customInjector.instance()

    @Test
    fun shouldRenderADriver() {
        presenter.onAttach(view)

        presenter.onVehicleIdAvailable(VEHICLE_ID)

        verify(view).renderDriver(any())
    }

    @Test
    fun shouldRenderAVehicleInfo() {
        whenever(vehicleMemoryDataSource.getVehicle(VEHICLE_ID)).thenReturn(Try.Success(createVehicle()))
        presenter.onAttach(view)

        presenter.onVehicleIdAvailable(VEHICLE_ID)

        verify(view).renderVehicleInfo(any())
    }

    private fun createVehicle() = Vehicle(VEHICLE_ID,
            FAKE,
            FAKE,
            GeoLocation(0.toDouble(), 0.toDouble()),
            Status.GOOD,
            Status.GOOD,
            0,
            Engine.COMBUSTION
    )
}