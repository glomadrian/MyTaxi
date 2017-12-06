package com.github.glomadrian.mytaxi.api.mytaxi.model

data class VehicleApiModel(
        val name: String,
        val vin: String,
        val address: String,
        val coordinates: List<Double>,
        val interior: State,
        val exterior: State,
        val fuel: Int,
        val engineType: EngineType) {

    enum class State {
        REALLY_GOOD, GOOD, BAD, UNACCEPTABLE
    }

    enum class EngineType {
        CE
    }
}

