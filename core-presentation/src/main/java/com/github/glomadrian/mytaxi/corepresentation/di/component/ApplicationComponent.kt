package com.github.glomadrian.mytaxi.corepresentation.di.component

import com.github.glomadrian.api.di.ApiModule
import com.github.glomadrian.cartelera.core.di.naming.JobExecutor
import com.github.glomadrian.cartelera.core.di.naming.ResponseExecutor
import com.github.glomadrian.corepresentation.app.CarteleraApplication
import com.github.glomadrian.corepresentation.di.module.ApplicationModule
import com.github.glomadrian.cartelera.core.di.scopes.PerApp
import com.github.glomadrian.corepresentation.navigation.Navigator
import com.github.glomadrian.data.di.DataModule
import com.github.glomadrian.domaincore.repository.CreditsRepository
import com.github.glomadrian.domaincore.repository.MoviesRepository
import com.github.glomadrian.domaincore.repository.RatingRepository
import com.github.glomadrian.domaincore.repository.VideosRepository
import com.github.glomadrian.mytaxi.api.di.ApiModule
import com.github.glomadrian.mytaxi.core.di.scopes.PerApp
import com.github.glomadrian.mytaxi.corepresentation.app.MyTaxiApplication
import com.github.glomadrian.mytaxi.corepresentation.di.module.ApplicationModule
import com.github.glomadrian.mytaxi.data.di.DataModule
import dagger.Component
import java.util.concurrent.Executor

@PerApp
@Component(modules = [(ApplicationModule::class), (DataModule::class), (ApiModule::class)])
interface ApplicationComponent {
    fun inject(myTaxiApplication: MyTaxiApplication)
    fun getMyTaxiApplication(): MyTaxiApplication
}
