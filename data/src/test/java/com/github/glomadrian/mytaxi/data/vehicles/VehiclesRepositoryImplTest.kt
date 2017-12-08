package com.github.glomadrian.mytaxi.data.vehicles

import com.github.glomadrian.mytaxi.core.exception.CoreException
import com.github.glomadrian.mytaxi.data.vehicles.datasource.VehicleCloudDataSource
import com.github.glomadrian.mytaxi.data.vehicles.datasource.VehicleMemoryDataSource
import com.github.glomadrian.mytaxi.domaincore.model.Engine
import com.github.glomadrian.mytaxi.domaincore.model.GeoLocation
import com.github.glomadrian.mytaxi.domaincore.model.Status
import com.github.glomadrian.mytaxi.domaincore.model.Vehicle
import com.nhaarman.mockito_kotlin.*
import org.funktionale.tries.Try
import org.junit.Test

class VehiclesRepositoryImplTest {

    companion object {
        private const val VEHICLE_ID = "WME4513341K565439"
        private const val FAKE = "fake"
    }

    private val mockCloudDataSource: VehicleCloudDataSource = mock()
    private val mockLocalDataSource: VehicleMemoryDataSource = mock()
    private val repository = VehiclesRepositoryImpl(mockCloudDataSource, mockLocalDataSource)

    @Test
    fun shouldStoreCarsInMemoryWhenArriveFromCloud(){
        whenever(mockCloudDataSource.getVehicles()).thenReturn(Try.Success(listOfVehicles()))

        repository.getAvailableVehicles()

        verify(mockLocalDataSource).store(any())
    }

    @Test
    fun shouldNotStoreCarsInMemoryIfThereAreAnError(){
        whenever(mockCloudDataSource.getVehicles()).thenReturn(Try.Failure(CoreException.NotFound()))

        repository.getAvailableVehicles()

        verify(mockLocalDataSource, times(0)).store(any())
    }


    private fun listOfVehicles() = listOf(createVehicle(), createVehicle())

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