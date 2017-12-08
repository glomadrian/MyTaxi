package com.github.glomadrian.mytaxi.testingpresentation.responses.mytaxiapi

import com.github.glomadrian.mytaxi.testingpresentation.ResponseBuilder
import com.github.glomadrian.mytaxi.testingpresentation.responses.mytaxiapi.data.validVehicleResponseData
import okhttp3.mockwebserver.MockWebServer

fun enqueueValidVehicleResponse(mockWebServer: MockWebServer) {
    mockWebServer.enqueue(ResponseBuilder().with200Ok().withBody(validVehicleResponseData).build())
}



