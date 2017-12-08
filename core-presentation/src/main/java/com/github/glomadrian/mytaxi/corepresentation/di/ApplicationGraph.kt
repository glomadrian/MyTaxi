package com.github.glomadrian.mytaxi.corepresentation.di

import com.github.glomadrian.mytaxi.corepresentation.navigator.Navigator
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.provider

val applicationGraph = Kodein.Module {
    bind<Navigator>() with provider { Navigator() }
}