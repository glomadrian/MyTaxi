package com.github.glomadrian.mytaxi.corepresentation.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.glomadrian.mytaxi.corepresentation.app.MyTaxiApplication
import com.github.glomadrian.mytaxi.corepresentation.di.component.ApplicationComponent

abstract class MyTaxiRenderer<T>(parent: ViewGroup) {

    lateinit var view: View

    init {
        initializeView(parent)
        val applicationComponent = (view.context.applicationContext as MyTaxiApplication).applicationComponent
        doInjection(applicationComponent)
    }

    protected abstract fun doInjection(applicationComponent: ApplicationComponent)

    private fun initializeView(parent: ViewGroup) {
        val layoutInflater = LayoutInflater.from(parent.context)
        view = provideView(parent, layoutInflater)
    }

    protected abstract fun provideView(viewGroup: ViewGroup, layoutInflater: LayoutInflater): View

    abstract fun render(viewModel: T)
}
