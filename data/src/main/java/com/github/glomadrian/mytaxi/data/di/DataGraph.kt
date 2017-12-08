package com.github.glomadrian.mytaxi.data.di

import com.github.glomadrian.mytaxi.data.di.internal.dataSourceGraph
import com.github.glomadrian.mytaxi.data.di.internal.repositoryGraph
import com.github.salomonbrys.kodein.Kodein

val dataGraph = Kodein.Module {
    import(dataSourceGraph)
    import(repositoryGraph)
}