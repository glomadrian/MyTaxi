package com.github.glomadrian.mytaxi.vehiclemap.presentation

import com.github.glomadrian.mytaxi.domaincore.model.Engine
import com.github.glomadrian.mytaxi.domaincore.model.GeoLocation
import com.github.glomadrian.mytaxi.domaincore.model.Status
import com.github.glomadrian.mytaxi.domaincore.model.Vehicle
import com.github.glomadrian.mytaxi.domainlogic.usecase.vehiclelist.GetAvailableVehiclesUseCase
import com.github.glomadrian.mytaxi.testingpresentation.UnitTest
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.funktionale.tries.Try
import org.junit.Test

class   VehicleMapPresenterTest: UnitTest() {

    companion object {
        private const val VEHICLE_ID = "WME4513341K565439"
        private const val FAKE = "FAKE"
    }

    private val getAvailableVehiclesUseCase: GetAvailableVehiclesUseCase = mock()
    private val view: VehicleMapPresenter.View = mock()
    private val presenter = VehicleMapPresenter(getAvailableVehiclesUseCase)

    @Test
    fun shouldCallUseCasesOnVehicleId() {
        presenter.onVehicleId(VEHICLE_ID)

        verify(getAvailableVehiclesUseCase).execute()
    }

    @Test
    fun shouldRenderIfUseCaseReturnVehicle(){
        whenever(getAvailableVehiclesUseCase.execute()).thenReturn(Try.Success(listOf(createVehicle())))
        presenter.onAttach(view)

        presenter.onVehicleId(VEHICLE_ID)

        verify(view).renderSelectedLocation(any())
        verify(view).renderVehicleLocations(any())
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