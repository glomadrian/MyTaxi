package com.github.glomadrian.mytaxi.data.drivers

import com.github.glomadrian.mytaxi.core.extensions.toTry
import com.github.glomadrian.mytaxi.domaincore.model.Driver
import com.github.glomadrian.mytaxi.domaincore.repository.DriverRepository
import java.util.*

internal class MockDriverRepository : DriverRepository {
    private val mockedDrivers = listOf(
            Driver("Adrian", "https://avatars2.githubusercontent.com/u/5353046?s=460&v=4"),
            Driver("Vegeta", "https://img00.deviantart.net/a541/i/2013/173/9/3/comision__majin_vegeta__by_naruto999_by_roker-d6a7i87.png"),
            Driver("Goku", "https://avatarfiles.alphacoders.com/782/78200.png"),
            Driver("Walter White", "https://vignette2.wikia.nocookie.net/breakingbad/images/1/1b/Walter_white_breaking_bad_by_sanposbc-d5l1mk2.jpg/revision/latest?cb=20140321204134&path-prefix=es"),
            Driver("Thor", "https://store.playstation.com/store/api/chihiro/00_09_000/container/US/en/999/UP0334-CUSA02328_00-AV00000000000026/1507841155000/image?_version=00_09_000&platform=chihiro&w=225&h=225&bg_color=000000&opacity=100"),
            Driver("Hulk", "https://avatarfiles.alphacoders.com/586/thumb-58630.jpg")
    )

    override fun getDriverForVehicle(vehicleId: String) = {
        mockedDrivers[Random().nextInt(mockedDrivers.size)]
    }.toTry()

}