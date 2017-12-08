package com.github.glomadrian.mytaxi.vehiclelist

import com.github.glomadrian.mytaxi.coretesting.IntegrationTest
import com.github.glomadrian.mytaxi.coretesting.di.integrationTestGraph
import com.github.glomadrian.mytaxi.vehiclelist.di.vehicleListGrahp
import com.github.glomadrian.mytaxi.vehiclelist.presentation.VehicleListPresenter
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.instance
import org.junit.Test
import org.mockito.Mock

class VehicleListPresenterIntegrationTest: IntegrationTest() {
    private val injector = Kodein {
        extend(integrationTestGraph)
        import(vehicleListGrahp)
    }

    @Mock lateinit var view: VehicleListPresenter.View
    private val presenter: VehicleListPresenter= injector.instance()

    @Test
    fun shouldRenderAValidListOfVehiclesWhenPresenterIsReady() {
        presenter.onAttach(view)
    }
}