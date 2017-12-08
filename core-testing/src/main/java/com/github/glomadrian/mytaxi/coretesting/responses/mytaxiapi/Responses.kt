package com.github.glomadrian.mytaxi.coretesting.responses.mytaxiapi

import com.github.glomadrian.mytaxi.coretesting.ResponseBuilder
import com.github.glomadrian.mytaxi.coretesting.responses.mytaxiapi.data.validVehicleResponseData
import okhttp3.mockwebserver.MockWebServer

fun enqueueValidVehicleResponse(mockWebServer: MockWebServer) {
    mockWebServer.enqueue(ResponseBuilder().with200Ok().withBody(validVehicleResponseData).build())
}



