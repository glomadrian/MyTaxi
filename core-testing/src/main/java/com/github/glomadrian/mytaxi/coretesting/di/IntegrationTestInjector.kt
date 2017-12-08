package com.github.glomadrian.mytaxi.coretesting.di

import com.github.glomadrian.mytaxi.api.di.apiGraph
import com.github.glomadrian.mytaxi.corepresentation.di.applicationGraph
import com.github.glomadrian.mytaxi.data.di.dataGraph
import com.github.glomadrian.mytaxi.domainlogic.usecase.di.useCaseGraph
import com.github.salomonbrys.kodein.Kodein

val integrationTestGraph = Kodein{
    import(apiGraph)
    import(dataGraph)
    import(useCaseGraph)
    import(applicationGraph)
}