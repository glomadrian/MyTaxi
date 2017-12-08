package com.github.glomadrian.mytaxi.coretesting.di

import com.github.glomadrian.mytaxi.corepresentation.di.applicationGraph
import com.github.glomadrian.mytaxi.data.di.dataGraph
import com.github.glomadrian.mytaxi.domainlogic.usecase.di.useCaseGraph
import com.github.salomonbrys.kodein.Kodein

val integrationTestGraph = Kodein {
    import(apiMockTestGraph, allowOverride = true)
    import(dataGraph, allowOverride = true)
    import(useCaseGraph, allowOverride = true)
    import(applicationGraph, allowOverride = true)
}