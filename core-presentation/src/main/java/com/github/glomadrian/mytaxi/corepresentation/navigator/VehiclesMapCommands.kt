package com.github.glomadrian.mytaxi.corepresentation.navigator

import android.net.Uri

val vehiclesMapNavigationCommand: (String) -> NavigatorCommand = { vehicleId ->
    val command: NavigatorCommand = { schema ->
        Uri.Builder()
                .scheme(schema)
                .authority("vehicles-map")
                .appendQueryParameter("vehicle.id.key", vehicleId)
                .build()
    }
    command
}
